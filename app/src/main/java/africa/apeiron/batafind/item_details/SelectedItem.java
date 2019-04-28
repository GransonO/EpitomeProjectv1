package africa.apeiron.batafind.item_details;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.bassaer.chatmessageview.model.Message;
import com.github.bassaer.chatmessageview.view.ChatView;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import africa.apeiron.batafind.R;
import africa.apeiron.batafind.sms_activities.Sms_Sender;
import cn.pedant.SweetAlert.SweetAlertDialog;
import info.hoang8f.android.segmented.SegmentedGroup;

public class SelectedItem extends AppCompatActivity {

    private ChatView mChatView;
    ImageView display_item;
    SegmentedGroup segmented_group;
    TextView shoe_name,product_id,product_price,product_size;
    String image_url;
    String name,sizes,code;
    String price,details_url;
    SweetAlertDialog pDialog;
    Context context;
    ArrayList<String> size_list = new ArrayList<>();

    Button availability_check;
    String size = "default";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_item);

        context = this;

        Intent X = getIntent();
        image_url = X.getStringExtra("shoe_image");
        name = X.getStringExtra("shoe_name");
        price = X.getStringExtra("shoe_price");
        details_url = "http://batakenya.com" + X.getStringExtra("shoe_link");

        final SegmentedGroup segmentedGroup = findViewById(R.id.segmented_buttons);
        segmented_group = segmentedGroup;
        segmented_group.setTintColor(Color.parseColor("#ffffff"), Color.parseColor("#ff0000"));


        availability_check = findViewById(R.id.availability_check);
        shoe_name = findViewById(R.id.shoe_name);
        product_id = findViewById(R.id.product_id);
        product_price = findViewById(R.id.product_price);
        product_size = findViewById(R.id.product_size);
        shoe_name.setText(name);
        new Description().execute();

        display_item = findViewById(R.id.selected_image);
        display_item.setClipToOutline(true);

        Picasso.get().load(image_url).into(display_item);

        chatRoom();

        //Chat Functions
        int yourId = 1;
        Bitmap yourIcon = BitmapFactory.decodeResource(getResources(), R.drawable.face_1);
        String yourName = "Morty";

        final User you = new User(yourId, yourName, yourIcon);
        //Receive message
        final Message receivedMessage = new Message.Builder()
                .setUser(you)
                .setRight(false)
                .setText("This is a message from the chat bot")
                .build();

        //Post received message to Chat View
        mChatView.receive(receivedMessage);

        availability_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(size.equals("default")){

                }else{

                    new Sms_Sender(SelectedItem.this,"Nairobi",code,size);
                }
            }
        });
    }

    //Chat Methods
    public void chatRoom(){
        //User id
        int myId = 0;
        //User icon
        Bitmap myIcon = BitmapFactory.decodeResource(getResources(), R.drawable.face_2);
        //User name
        String myName = "Granson";

        final User me = new User(myId, myName, myIcon);

        mChatView = findViewById(R.id.chat_view);

        //Set UI parameters if you need
        mChatView.setRightBubbleColor(ContextCompat.getColor(this, R.color.green500));
        mChatView.setLeftBubbleColor(Color.GRAY);
        mChatView.setSendButtonColor(ContextCompat.getColor(this, R.color.red500));
        mChatView.setSendIcon(R.drawable.ic_action_send);
        mChatView.setRightMessageTextColor(Color.WHITE);
        mChatView.setLeftMessageTextColor(Color.WHITE);
        mChatView.setUsernameTextColor(Color.WHITE);
        mChatView.setSendTimeTextColor(Color.WHITE);
        mChatView.setDateSeparatorColor(Color.WHITE);
        mChatView.setInputTextHint("Your comment...");
        mChatView.setMessageFontSize(28);
        mChatView.setMessageMarginTop(5);
        mChatView.setUsernameFontSize(28);
        mChatView.setDateSeparatorColor(Color.WHITE);
        mChatView.setDateSeparatorFontSize(28);
        mChatView.setMessageMarginBottom(5);

        //Click Send Button
        mChatView.setOnClickSendButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //new message
                Message message = new Message.Builder()
                        .setUser(me)
                        .setRight(true)
                        .setText(mChatView.getInputText())
                        .hideIcon(true)
                        .build();
                //Set to chat view
                mChatView.send(message);
                //Reset edit text
                mChatView.setInputText("");

            }

        });

    }

    private class Description extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
            pDialog.getProgressHelper().setBarColor(Color.parseColor("#ff0000"));
            pDialog.setTitleText("Fetching...");
            pDialog.setContentText("Getting available sizes.");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Document document = Jsoup.connect(details_url).get();

                Elements shoe_code = document.getElementsByClass("product_art_code");
                code = shoe_code.html();

                Elements shoe_sizes = document.getElementsByClass("jQuerySizeDefaultEvent");
                for (Element shoe_size : shoe_sizes) {
                    Elements values = shoe_size.select("input");
                    size_list.add(values.attr("value"));
                }
                sizes = size_list.toString();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pDialog.dismissWithAnimation();

            String id = code.replace("Product Code:","");
            String is = (sizes.replace("[,","")).replace("]","");
            product_price.setText("KES " + price);
            product_id.setText(id);
            //product_size.setText(is);

            String[] sizes = is.split(",");

            for(int i = 0; i < sizes.length; i++){
                RadioButton radioButton = (RadioButton) getLayoutInflater().inflate(R.layout.radio_button_item, null);
                radioButton.setText(sizes[i]);
                radioButton.setId(i);
                segmented_group.addView(radioButton);
                segmented_group.updateBackground();
            }

            segmented_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    int selectedRadioButtonId = segmented_group.getCheckedRadioButtonId();
                    RadioButton rbtn = findViewById(selectedRadioButtonId);
                    size = rbtn.getText().toString();
                    Toast.makeText(context,"Size " + rbtn.getText() + " selected",Toast.LENGTH_LONG).show();

                }
            });
        }
    }
}
