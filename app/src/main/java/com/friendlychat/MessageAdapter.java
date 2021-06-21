package com.friendlychat;


import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class MessageAdapter extends ArrayAdapter<FriendlyMessage> {

    Context mcontext;
    public MessageAdapter(Context context, int resource, List<FriendlyMessage> objects) {
        super(context, resource, objects);
        mcontext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_message, parent, false);
        }

        ImageView photoImageView = (ImageView) convertView.findViewById(R.id.photoImageView);
        ImageView MyphotoImageView = (ImageView) convertView.findViewById(R.id.MyphotoImageView);

        TextView messageTextView = (TextView) convertView.findViewById(R.id.messageTextView);
        TextView authorTextView = (TextView) convertView.findViewById(R.id.nameTextView);

        TextView MymessageTextView = (TextView) convertView.findViewById(R.id.MymessageTextView);
        TextView MyauthorTextView = (TextView) convertView.findViewById(R.id.MynameTextView);


        FriendlyMessage message = getItem(position);

        boolean isPhoto = message.getPhotoUrl() != null;
        if (isPhoto) {
            if(MainActivity.UserID.equals(message.getUser_ID())){
                MyphotoImageView.setVisibility(View.VISIBLE);
                MyauthorTextView.setVisibility(View.VISIBLE);
                Glide
                        .with(MyphotoImageView.getContext())
                        .load(message.getPhotoUrl())
                        .into(MyphotoImageView);
                MyauthorTextView.setText(message.getName());

                MymessageTextView.setVisibility(View.GONE);
                messageTextView.setVisibility(View.GONE);
                authorTextView.setVisibility(View.GONE);
                photoImageView.setVisibility(View.GONE);

            }else{

                photoImageView.setVisibility(View.VISIBLE);
                authorTextView.setVisibility(View.VISIBLE);
                Glide
                        .with(photoImageView.getContext())
                        .load(message.getPhotoUrl())
                        .into(photoImageView);
                authorTextView.setText(message.getName());

                MymessageTextView.setVisibility(View.GONE);
                MyauthorTextView.setVisibility(View.GONE);
                messageTextView.setVisibility(View.GONE);
                MyphotoImageView.setVisibility(View.GONE);
            }

        } else {

            if(MainActivity.UserID.equals(message.getUser_ID())){

                MymessageTextView.setVisibility(View.VISIBLE);
                MyauthorTextView.setVisibility(View.VISIBLE);

                MymessageTextView.setText(message.getText());
                MyauthorTextView.setText(message.getName());

                photoImageView.setVisibility(View.GONE);
                MyphotoImageView.setVisibility(View.GONE);

                messageTextView.setVisibility(View.GONE);
                authorTextView.setVisibility(View.GONE);
            }else {

                messageTextView.setVisibility(View.VISIBLE);
                authorTextView.setVisibility(View.VISIBLE);

                messageTextView.setText(message.getText());
                authorTextView.setText(message.getName());

                photoImageView.setVisibility(View.GONE);
                MyphotoImageView.setVisibility(View.GONE);

                MymessageTextView.setVisibility(View.GONE);
                MyauthorTextView.setVisibility(View.GONE);
            }
        }

        return convertView;
    }
}
