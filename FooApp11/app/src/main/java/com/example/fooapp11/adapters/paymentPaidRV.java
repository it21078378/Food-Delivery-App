package com.example.fooapp11.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fooapp11.R;
import com.example.fooapp11.model.PaidModel;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class paymentPaidRV extends FirebaseRecyclerAdapter<PaidModel, paymentPaidRV.viewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public paymentPaidRV(@NonNull FirebaseRecyclerOptions<PaidModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull viewHolder holder, int position, @NonNull PaidModel model) {
        holder.name.setText(model.getName());
        holder.price.setText( String.valueOf(model.getTotal()));
        holder.qty.setText( String.valueOf(model.getQuantity()));

        Glide.with(holder.img.getContext())
                .load(model.getImg())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(com.google.android.gms.base.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);

        //continue
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.payment_history_card,parent, false);
        return new viewHolder(view);
    }

    class viewHolder extends RecyclerView.ViewHolder{
        CircleImageView img;
        TextView name, qty, price;


        public viewHolder(@NonNull View itemView) {
            super(itemView);

            img = (CircleImageView)itemView.findViewById(R.id.pCardImg);
            name = (TextView)itemView.findViewById(R.id.pCardName);
            qty = (TextView)itemView.findViewById(R.id.pCardQty);
            price = (TextView)itemView.findViewById(R.id.pCardPrice);
        }
    }
}
