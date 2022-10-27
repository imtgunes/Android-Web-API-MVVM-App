package com.example.webapimvvm.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.webapimvvm.ui.FragmentAddToCart;
import com.example.webapimvvm.model.Product;
import com.example.webapimvvm.R;
import com.example.webapimvvm.databinding.ItemProductBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private ArrayList<Product> productArrayList;
    private Context context;
    private int userID;
    private int shoppingCartID;
    private BottomSheetDialogFragment fragmentAddToCart = new FragmentAddToCart();
    private FragmentManager fragmentManager;

    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemProductBinding itemProductBinding;
        public ViewHolder(ItemProductBinding itemProductBinding) {
            super(itemProductBinding.getRoot());
            this.itemProductBinding = itemProductBinding;
        }
    }

    public ProductAdapter(ArrayList<Product> itemLists, Context context, int userID, int shoppingCartID) {
        productArrayList = itemLists;
        this.context = context;
        this.userID = userID;
        this.shoppingCartID = shoppingCartID;

    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductBinding itemProductBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_product, parent, false);
        return new ViewHolder(itemProductBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        final Product product = productArrayList.get(position);
        holder.itemProductBinding.textViewProductItemName.setText(product.getProductName());
        holder.itemProductBinding.textViewProductItemWeight.setText(String.valueOf(product.getProductWeight()+" g"));
        Picasso.get().load(product.getProductImage()).fit().centerCrop().into(holder.itemProductBinding.imageViewProductItem);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentAddToCart.setStyle(FragmentAddToCart.STYLE_NORMAL,R.style.MyTransparentBottomSheetDialogTheme);
                FrameLayout frameLayout = ((AppCompatActivity) context).findViewById(R.id.frameLayaut);
                fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();

                if(fragmentManager.findFragmentByTag("newTAG") != null && fragmentManager.findFragmentByTag("newTAG").isAdded())
                    return;
                if (fragmentManager.findFragmentByTag("newTAG") == null) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("userID",userID);
                    bundle.putInt("shoppingCartID",shoppingCartID);
                    bundle.putInt("productID",product.getProductID());
                    bundle.putString("productName",product.getProductName());
                    bundle.putString("productImage",product.getProductImage());
                    bundle.putFloat("productWeight",product.getProductWeight());
                    fragmentAddToCart.setArguments(bundle);
                    fragmentAddToCart.show(((AppCompatActivity) context).getSupportFragmentManager(), "newTAG");
                    frameLayout.setVisibility(View.VISIBLE);
                }
                else{
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.addToBackStack(null);
                    Bundle bundle = new Bundle();
                    bundle.putInt("userID",userID);
                    bundle.putInt("shoppingCartID",shoppingCartID);
                    bundle.putInt("productID",product.getProductID());
                    bundle.putString("productName",product.getProductName());
                    bundle.putString("productImage",product.getProductImage());
                    bundle.putFloat("productWeight",product.getProductWeight());
                    fragmentAddToCart.setArguments(bundle);
                    fragmentTransaction.add(R.id.frameLayaut,fragmentAddToCart);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    fragmentAddToCart.show(((AppCompatActivity) context).getSupportFragmentManager(),"newTAG");
                    frameLayout.setVisibility(View.VISIBLE);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }
}