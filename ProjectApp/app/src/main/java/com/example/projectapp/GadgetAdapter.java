package com.example.projectapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GadgetAdapter extends RecyclerView.Adapter<GadgetAdapter.GadgetViewHolder> {

    public List<Gadget> gadgetList;
    private Context context;

    public GadgetAdapter(Context context, List<Gadget> gadgetList) {
        this.context = context;
        this.gadgetList = gadgetList;
    }

    public void setFilteredList(List<Gadget> filteredList) {
        this.gadgetList = filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GadgetAdapter.GadgetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gadget_item, parent, false);
        GadgetViewHolder holder = new GadgetViewHolder(view, context, gadgetList);

        holder.itemView.setOnClickListener(v -> {
            int position = holder.getBindingAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                Gadget gadgetItem = gadgetList.get(position);
                Intent intent = new Intent(context, GadgetDetailActivity.class);
                intent.putExtra("GadgetImgView", gadgetItem.getImageUri().toString());
                intent.putExtra("GadgetName", gadgetItem.getName());
                intent.putExtra("GadgetDescription", gadgetItem.getDescription());
                intent.putExtra("GadgetUrl", gadgetItem.getUrl());
                context.startActivity(intent);
            }
        });

        holder.itemView.setOnLongClickListener(v -> {
            int position = holder.getBindingAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                showPopupMenu(v, position);
                return true;
            }
            return false;
        });

        return holder;
    }

    private void showPopupMenu(View view, final int position) {
        PopupMenu popup = new PopupMenu(context, view);
        popup.inflate(R.menu.item_menu);
        popup.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.menu_delete) {
                deleteItem(position);
                return true;
            }
            return false;
        });
        popup.show();
    }

    private void deleteItem(int position) {
        gadgetList.remove(position);
        notifyItemRemoved(position);
        Toast.makeText(context, "Item deleted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBindViewHolder(@NonNull GadgetAdapter.GadgetViewHolder holder, int position) {
        Gadget gadget = gadgetList.get(position);
        holder.gadgetName.setText(gadget.getName());
        holder.gadgetColor.setText(gadget.getColor());
        holder.gadgetStorage.setText(gadget.getStorage());
        holder.gadgetPrice.setText(gadget.getPrice());
        holder.gadgetCPU.setText(gadget.getCpu());
        holder.gadgetRAM.setText(gadget.getRam());
        holder.gadgetOs.setText(gadget.getOs());
        holder.gadgetDisplay.setText(gadget.getDisplay());
        holder.gadgetCamera.setText(gadget.getCamera());
        holder.gadgetBattery.setText(gadget.getBattery());

        // Set image using Uri
        if (gadget.hasImageUri()) {
            try {
                holder.gadgetImage.setImageURI(gadget.getImageUri());
            } catch (SecurityException e) {
                holder.gadgetImage.setImageResource(R.drawable.iphone13); // Default image in case of security exception
                e.printStackTrace();
            }
        } else if (gadget.hasImageResourceId()) {
            holder.gadgetImage.setImageResource(gadget.getImageResourceId());
        } else {
            holder.gadgetImage.setImageResource(R.drawable.iphone12); // Default image if no image is provided
        }
    }

    @Override
    public int getItemCount() {
        return gadgetList.size();
    }

    public static class GadgetViewHolder extends RecyclerView.ViewHolder {
        TextView gadgetName, gadgetColor, gadgetStorage, gadgetPrice, gadgetCPU, gadgetRAM, gadgetOs, gadgetDisplay, gadgetCamera, gadgetBattery;
        ImageView gadgetImage;

        public GadgetViewHolder(@NonNull View itemView, final Context context, List<Gadget> gadgetList) {
            super(itemView);

            gadgetName = itemView.findViewById(R.id.gadget_name);
            gadgetColor = itemView.findViewById(R.id.gadget_color);
            gadgetStorage = itemView.findViewById(R.id.gadget_storage);
            gadgetPrice = itemView.findViewById(R.id.gadget_price);
            gadgetImage = itemView.findViewById(R.id.gadget_image);
            gadgetCPU = itemView.findViewById(R.id.CPU);
            gadgetRAM = itemView.findViewById(R.id.RAM);
            gadgetOs = itemView.findViewById(R.id.OS);
            gadgetDisplay = itemView.findViewById(R.id.ScreenDisplay);
            gadgetCamera = itemView.findViewById(R.id.Camera);
            gadgetBattery = itemView.findViewById(R.id.Battery);
        }
    }
}
