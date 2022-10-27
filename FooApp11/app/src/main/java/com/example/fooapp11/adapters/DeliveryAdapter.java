package com.example.fooapp11.adapters;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.fooapp11.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooapp11.model.DeliveryModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Date;

public class DeliveryAdapter extends RecyclerView.Adapter<DeliveryAdapter.ViewHolder>{

    Context context;
    ArrayList<DeliveryModel> deliveryModelArrayList;
    DatabaseReference databaseReference;

    public DeliveryAdapter(Context context, ArrayList<DeliveryModel> deliveryModelArrayList) {
        this.context = context;
        this.deliveryModelArrayList = deliveryModelArrayList;
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.address_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        DeliveryModel addresses = deliveryModelArrayList.get(position);

        holder.textHNo.setText("Apt or House No : " + addresses.getHNo());
        holder.textBuilding.setText("Business or building name : " + addresses.getBuilding());
        holder.textStreet.setText("Street number and name : " + addresses.getStreet());
        holder.textArea.setText("Area/District : " + addresses.getArea());
        holder.textLandmark.setText("Landmark : " + addresses.getLandmark());

        holder.buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewDialogUpdate viewDialogUpdate = new ViewDialogUpdate();
                viewDialogUpdate.showDialog(context, addresses.getId(), addresses.getHNo(), addresses.getBuilding(), addresses.getStreet(), addresses.getArea(), addresses.getLandmark());
            }
        });

        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewDialogConfirmDelete viewDialogConfirmDelete = new ViewDialogConfirmDelete();
                viewDialogConfirmDelete.showDialog(context, addresses.getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return deliveryModelArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView textHNo;
        TextView textBuilding;
        TextView textStreet;
        TextView textArea;
        TextView textLandmark;

        Button buttonDelete;
        Button buttonUpdate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textHNo = itemView.findViewById(R.id.textHNo);
            textBuilding = itemView.findViewById(R.id.textBuilding);
            textStreet = itemView.findViewById(R.id.textStreet);
            textArea = itemView.findViewById(R.id.textArea);
            textLandmark = itemView.findViewById(R.id.textLandmark);

            buttonDelete = itemView.findViewById(R.id.buttonDelete);
            buttonUpdate = itemView.findViewById(R.id.buttonUpdate);

        }

    }


    public class ViewDialogUpdate {
        public void showDialog(Context context, String id, String HNo, String building, String street, String area, String landmark) {
            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.alert_dialog_add_new_address);

            EditText textHNo = dialog.findViewById(R.id.textHNo);
            EditText textBuilding = dialog.findViewById(R.id.textBuilding);
            EditText textStreet = dialog.findViewById(R.id.textStreet);
            EditText textArea = dialog.findViewById(R.id.textArea);
            EditText textLandmark = dialog.findViewById(R.id.textLandmark);

            textHNo.setText(HNo);
            textBuilding.setText(building);
            textStreet.setText(street);
            textArea.setText(area);
            textLandmark.setText(landmark);


            Button buttonUpdate = dialog.findViewById(R.id.buttonAdd);
            Button buttonCancel = dialog.findViewById(R.id.buttonCancel);

            buttonUpdate.setText("UPDATE");

            buttonCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            buttonUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String newHNo = textHNo.getText().toString();
                    String newBuilding = textBuilding.getText().toString();
                    String newStreet = textStreet.getText().toString();
                    String newArea = textArea.getText().toString();
                    String newLandmark = textLandmark.getText().toString();



                    if (HNo.isEmpty() || building.isEmpty() || street.isEmpty() || area.isEmpty() || landmark.isEmpty()) {
                        Toast.makeText(context, "Please Enter All data...", Toast.LENGTH_SHORT).show();
                    } else {

                        if (newHNo.equals(HNo) && newBuilding.equals(building) && newStreet.equals(street) && newArea.equals(area) && newLandmark.equals(landmark)) {
                            Toast.makeText(context, "you don't change anything", Toast.LENGTH_SHORT).show();
                        } else {
                            databaseReference.child("ADDRESSES").child(id).setValue(new DeliveryModel(id, newHNo, newBuilding, newStreet, newArea, newLandmark));
                            Toast.makeText(context, "Address Updated successfully!", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }


                    }
                }
            });

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        }
    }



    public class ViewDialogConfirmDelete {
        public void showDialog(Context context, String id) {
            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.view_dialog_confirm_delete);

            Button buttonDelete = dialog.findViewById(R.id.buttonDelete);
            Button buttonCancel = dialog.findViewById(R.id.buttonCancel);

            buttonCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    databaseReference.child("ADDRESSES").child(id).removeValue();
                    Toast.makeText(context, "Address Deleted successfully!", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();

                }
            });

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        }
    }


}