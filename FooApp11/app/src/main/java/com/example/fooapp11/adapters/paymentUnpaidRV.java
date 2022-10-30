package com.example.fooapp11.adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fooapp11.CardDetailsForm;
import com.example.fooapp11.R;
import com.example.fooapp11.UnpaidCardDetails;
import com.example.fooapp11.model.UnpaidModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

public class paymentUnpaidRV extends FirebaseRecyclerAdapter<UnpaidModel, paymentUnpaidRV.viewHolder> {

    String Name, Qty, img , description;
    double total;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public paymentUnpaidRV(@NonNull FirebaseRecyclerOptions<UnpaidModel> options) {
        super(options);
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.payment_history_unpaid_card,parent, false);
        return new viewHolder(view);
    }

    class viewHolder extends RecyclerView.ViewHolder{
        CircleImageView img;
        TextView name, qty, price, description;

        Button btnDelete;
        Button btnPay;


        public viewHolder(@NonNull View itemView) {
            super(itemView);

            img = (CircleImageView)itemView.findViewById(R.id.pUnpaidCardImg);
            name = (TextView)itemView.findViewById(R.id.pUnpaidCardName);
            qty = (TextView)itemView.findViewById(R.id.pUnpaidCardQty);
            price = (TextView)itemView.findViewById(R.id.pUnpaidCardPrice);

            btnDelete = (Button)itemView.findViewById(R.id.deleteBtn);
            btnPay  =(Button)itemView.findViewById(R.id.unpPayBtn);

        }
    }
    @Override
    protected void onBindViewHolder(@NonNull viewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull UnpaidModel model) {



        holder.name.setText(model.getName());
        holder.price.setText( String.valueOf(model.getTotal()));
        holder.qty.setText( String.valueOf(model.getQuantity()));



        Glide.with(holder.img.getContext())
                .load(model.getImg())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(com.google.android.gms.base.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.name.getContext());
                builder.setTitle("Are u sure?");
                builder.setMessage("Deleted data can't be undo. ");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        FirebaseDatabase.getInstance().getReference().child("unpaidDetails")
                                .child(getRef(position).getKey()).removeValue();

                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        Toast.makeText(holder.name.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
        holder.btnPay.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Name=holder.name.getText().toString();
                Qty=holder.qty.getText().toString();
                total=model.getTotal();
                img= model.getImg();


                AlertDialog.Builder builder=new AlertDialog.Builder(holder.name.getContext());
                builder.setTitle("Are u sure?");
                builder.setMessage("This data can't be undo. ");

                builder.setPositiveButton("Pay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        FirebaseDatabase.getInstance().getReference().child("unpaidDetails")
                                .child(getRef(position).getKey()).removeValue();
                        sendData(view);

                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        Toast.makeText(holder.name.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });

    }



    private void sendData(View view) {
        Intent i = new Intent(view.getContext(), UnpaidCardDetails.class);

        i.putExtra(CardDetailsForm.Name, Name);
        i.putExtra(CardDetailsForm.Description, description);
        i.putExtra(CardDetailsForm.Img, img);
        i.putExtra(String.valueOf(CardDetailsForm.Total), total);
        i.putExtra(CardDetailsForm.quantity, Qty);


        view.getContext().startActivity(i);
    }




}
