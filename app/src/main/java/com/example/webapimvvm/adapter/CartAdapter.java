package com.example.webapimvvm.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.webapimvvm.model.Cart;
import com.example.webapimvvm.R;
import com.example.webapimvvm.ui.CartActivity;
import com.example.webapimvvm.viewmodel.CartActivityViewModel;
import com.example.webapimvvm.databinding.ItemCartBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private ArrayList<Cart> cartArrayList;
    private Context context;
    private CartActivityViewModel cartActivityViewModel;
    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemCartBinding itemCartBinding;
        public ViewHolder(ItemCartBinding itemCartBinding) {
            super(itemCartBinding.getRoot());
            this.itemCartBinding = itemCartBinding;
        }
    }

    public CartAdapter(ArrayList<Cart> itemLists, Context context) {
        cartArrayList = itemLists;
        this.context = context;
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        cartActivityViewModel = new CartActivityViewModel();
        ItemCartBinding itemCartBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_cart, parent, false);
        return new ViewHolder(itemCartBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        final Cart cart = cartArrayList.get(position);
        holder.itemCartBinding.textViewCartName.setText(cart.getProduct().getProductName());
        holder.itemCartBinding.textViewCartlWeight.setText(String.valueOf(cart.getProduct().getProductWeight())+" g");
        holder.itemCartBinding.textViewCartPiece.setText("Adet: "+String.valueOf(cart.getShoppingCartsPiece()));
        holder.itemCartBinding.textViewCartNote.setText(cart.getShoppingCartsNote());
        Picasso.get().load(cart.getProduct().getProductImage()).fit().centerCrop().into(holder.itemCartBinding.imageViewCart);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Ürünü sepetten çıkarıyorsunuz");
                builder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        cartActivityViewModel.removeItemOnCart(cart.getShoppingCartsID());
                        ((CartActivity)context).getShoppingCart();
                    }
                });
                builder.setNegativeButton("Vazgeç", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartArrayList.size();
    }
}