package com.example.webapimvvm.ui;



import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.webapimvvm.model.Cart;
import com.example.webapimvvm.R;
import com.example.webapimvvm.viewmodel.CartActivityViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;



public class FragmentAddToCart extends BottomSheetDialogFragment {
    private ImageButton imageButtonExit;
    private Button buttonAddToCart;
    private ImageView imageViewPiecePlus,imageViewPieceMinus,imageViewProductImage;
    private TextView textViewPieceFragment,textViewProductNameFragment,textViewProductWeight;
    private EditText editTextNote;
    private int productPiece = 1;
    private String cartNote = "";

    private int shoppingCartID;
    private int productID;
    private int userID;

    private CartActivityViewModel cartActivityViewModel;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_to_cart, container, false);
        getDialog().setCanceledOnTouchOutside(true);

        userID = this.getArguments().getInt("userID",0);
        productID = this.getArguments().getInt("productID",0);
        shoppingCartID = this.getArguments().getInt("shoppingCartID",0);
        String productName = this.getArguments().getString("productName");
        String prodcutImage = this.getArguments().getString("productImage");
        Float productWeight = this.getArguments().getFloat("productWeight");

        imageButtonExit = view.findViewById(R.id.imageButtonExit);
        imageViewPiecePlus = view.findViewById(R.id.imageViewPlusFragment);
        imageViewPieceMinus = view.findViewById(R.id.imageViewMinusFragment);
        textViewPieceFragment = view.findViewById(R.id.textViewProductPieceFragment);
        textViewProductNameFragment = view.findViewById(R.id.textViewProductNameFragment);
        imageViewProductImage = view.findViewById(R.id.imageViewToDoDetail);

        textViewProductWeight = view.findViewById(R.id.textViewProductWeightFragment);
        editTextNote = view.findViewById(R.id.editTextTextNoteFragment);

        textViewProductNameFragment.setText(productName);
        textViewProductWeight.setText(String.valueOf(productWeight)+" g");

        Picasso.get().load(prodcutImage).into(imageViewProductImage);

        imageViewPiecePlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productPiece = Integer.parseInt(textViewPieceFragment.getText().toString());

                productPiece++;
                textViewPieceFragment.setText(String.valueOf(productPiece));

            }
        });

        imageViewPieceMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productPiece = Integer.parseInt(textViewPieceFragment.getText().toString());
                if(productPiece > 1){
                    productPiece--;
                    textViewPieceFragment.setText(String.valueOf(productPiece));
                }


            }
        });

        imageButtonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextNote.setText("");
                hideSoftKeyboard(getActivity());
                FragmentAddToCart.this.dismiss();

            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cartActivityViewModel = new ViewModelProvider(requireActivity()).get(CartActivityViewModel.class);


        buttonAddToCart = view.findViewById(R.id.buttonAddToCartFragment);
        Activity activity = getActivity();
        buttonAddToCart.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                cartNote = String.valueOf(editTextNote.getText());
               Cart cart = new Cart(shoppingCartID,productPiece,cartNote,productID,null);
               cartActivityViewModel.getAddedCart().observe(getViewLifecycleOwner(), new Observer<Cart>() {
                   @Override
                   public void onChanged(Cart cart) {

                   }
               });
                cartActivityViewModel.addCart(cart);
                View view1 = activity.findViewById(android.R.id.content);
                Snackbar snackbar = Snackbar.make(view1,"Ürün eklendi ",Snackbar.LENGTH_SHORT)
                        .setAction("Sepete Git", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(activity, CartActivity.class);
                                intent.putExtra("shoppingCartID",shoppingCartID);
                                intent.putExtra("userID",userID);
                                activity.startActivity(intent);
                            }
                        });
                snackbar.show();

                productPiece = 1;
                editTextNote.setText("");
                FragmentAddToCart.this.dismiss();
                hideSoftKeyboard(getActivity());
            }
        });
    }

    public static void hideSoftKeyboard(Activity activity) {
        if (activity.getCurrentFocus() == null) {
            return;
        }
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }
}