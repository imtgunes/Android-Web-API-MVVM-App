package com.example.webapimvvm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.webapimvvm.model.Cart;
import com.example.webapimvvm.R;
import com.example.webapimvvm.databinding.ItemLastBinding;
import com.example.webapimvvm.databinding.ItemTodoDetailBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TodoDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Cart> cartArrayList;
    private Context context;
    private String shoppingCartNote;

    class ViewHolderOne extends RecyclerView.ViewHolder {
        ItemTodoDetailBinding itemTodoDetailBinding;
        public ViewHolderOne(@NonNull ItemTodoDetailBinding itemTodoDetailBinding) {
            super(itemTodoDetailBinding.getRoot());
            this.itemTodoDetailBinding = itemTodoDetailBinding;
        }
    }

    class ViewHolderTwo extends RecyclerView.ViewHolder {
        ItemLastBinding itemLastBinding;
        public ViewHolderTwo(@NonNull ItemLastBinding itemLastBinding) {
            super(itemLastBinding.getRoot());
            this.itemLastBinding = itemLastBinding;

        }
    }

    public TodoDetailAdapter(ArrayList<Cart> itemLists, Context context,String shoppingCartNote) {
        cartArrayList = itemLists;
        this.context = context;
        this.shoppingCartNote = shoppingCartNote;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount()-1) {
            return 0;
        }
        return 1;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            ItemLastBinding itemLastBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_last, parent, false);
            return new ViewHolderTwo(itemLastBinding);
        }

        ItemTodoDetailBinding itemTodoDetailBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_todo_detail, parent, false);
        return new ViewHolderOne(itemTodoDetailBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == 0) {
            ViewHolderTwo viewHolderTwo = (ViewHolderTwo) holder;
            if(shoppingCartNote == " " || shoppingCartNote == null || shoppingCartNote.isEmpty()){
                viewHolderTwo.itemLastBinding.textViewToDoDetailToDoNote.setVisibility(View.GONE);
                viewHolderTwo.itemLastBinding.textView3.setVisibility(View.GONE);
                viewHolderTwo.itemLastBinding.cardViewTodoNote.setVisibility(View.GONE);

            }else{
                viewHolderTwo.itemLastBinding.textViewToDoDetailToDoNote.setText(shoppingCartNote);

            }

        }else {
            final Cart cart = cartArrayList.get(position);
            ViewHolderOne viewHolderOne = (ViewHolderOne) holder;
            viewHolderOne.itemTodoDetailBinding.textViewToDoDetailName.setText(cart.getProduct().getProductName());
            viewHolderOne.itemTodoDetailBinding.textViewToDoDetailPiece.setText("Adet "+String.valueOf(cart.getShoppingCartsPiece()));
            viewHolderOne.itemTodoDetailBinding.textViewToDoDetailWeight.setText(cart.getProduct().getProductWeight().toString()+" g");
            Picasso.get().load(cart.getProduct().getProductImage()).fit().centerCrop().into(viewHolderOne.itemTodoDetailBinding.imageViewToDoDetail);

        }
    }

    @Override
    public int getItemCount() {
        return cartArrayList.size()+1;
    }
}