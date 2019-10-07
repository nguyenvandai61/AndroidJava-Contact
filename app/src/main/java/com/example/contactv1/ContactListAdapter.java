package com.example.contactv1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactHolder> {
    private Context context;
    private final List<Contact> listFull;
    //    private final List<Contact> filteredList;
    TextView tvName;
    ImageView civAvatar;
    Button btnCall;
    LinearLayout llItem;

    static Intent intentSendEditContact;


    public class ContactHolder extends RecyclerView.ViewHolder {
        ContactHolder(View itemView) {
            super(itemView);
            llItem = itemView.findViewById(R.id.ll_item);
            tvName = itemView.findViewById(R.id.tv_item_name);
            civAvatar = itemView.findViewById(R.id.civ_avatar);
            intentSendEditContact = new Intent(context, EditContact.class);
            btnCall = itemView.findViewById(R.id.btn_call);
        }
    }

    public ContactListAdapter(Context context, List<Contact> exampleList) {
        this.context = context;
//        this.lí = exampleList;
        System.out.println("Adapter get list size: " + exampleList.size());
        listFull = exampleList;
    }

    @NonNull
    @Override
    public ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_item, parent, false);
        return new ContactHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactListAdapter.ContactHolder holder, final int position) {
        tvName.setText(listFull.get(position).getmName());
        byte[] byteArr = listFull.get(position).getmAvatar();
        if (byteArr != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArr, 0, byteArr.length);
            civAvatar.setImageBitmap(bitmap);
        } else
            civAvatar.setImageResource(R.drawable.ic_contact);
        // Xu ly Call button
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = String.format("tel:%s", listFull.get(position).getmMobile());
                Uri uri = Uri.parse(number);
                Intent intentCall = new Intent(Intent.ACTION_DIAL);
                intentCall.setData(uri);
                context.startActivity(intentCall);
            }
        });

        llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contact contact = listFull.get(position);
                intentSendEditContact.putExtra("contact", contact);
                intentSendEditContact.putExtra("position", position);
                context.startActivity(intentSendEditContact);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listFull.size();
    }

//    @Override
//    public Filter getFilter() {
//        return filter;
//    }

//    private Filter filter = new Filter() {
//        @Override
//        protected FilterResults performFiltering(CharSequence constraint) {
//            List<Contact> filteredList = new ArrayList<>();
//
//            if (constraint == null || constraint.length() == 0) {
//                filteredList.addAll(listFull);
//            } else {
//                String filterPattern = constraint.toString().toLowerCase().trim();
//
//                for (Contact item : listFull) {
//                    if (item.getmName().toLowerCase().contains(filterPattern)) {
//                        filteredList.add(item);
//                    }
//                }
//            }
//
//            FilterResults results = new FilterResults();
//            results.values = filteredList;
//
//            return results;
//        }
//
//        @Override
//        protected void publishResults(CharSequence constraint, FilterResults results) {
//            filteredList.clear();
//            filteredList.addAll((List) results.values);
//            notifyDataSetChanged();
//        }
//    };
}
