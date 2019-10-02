package com.example.contactv1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ContactListAdapter extends ArrayAdapter<Contact> {
    private final Context context;
    private final ArrayList<Contact> values;
    TextView tvName;
    ImageView civAvatar;

    static Intent intentSendEditContact;

    public ContactListAdapter(Context context, ArrayList<Contact> values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.contact_item, parent, false);
        initWidget(rowView, position);
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contact contact = values.get(position);
                intentSendEditContact.putExtra("contact", contact);
                intentSendEditContact.putExtra("position", position);
                context.startActivity(intentSendEditContact);
            }
        });
        return rowView;
    }

    public void initWidget(View rowView, int position) {
        tvName = rowView.findViewById(R.id.tv_item_name);
        civAvatar = rowView.findViewById(R.id.civ_avatar);
        intentSendEditContact = new Intent(context, EditContact.class);

        tvName.setText(values.get(position).getmName());
        byte[] byteArr = values.get(position).getmAvatar();
        if (byteArr != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArr, 0, byteArr.length);
            civAvatar.setImageBitmap(bitmap);
        }
        else
            civAvatar.setImageResource(R.drawable.ic_contact);
    }
}
