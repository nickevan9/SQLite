package com.example.sqlite.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlite.R;
import com.example.sqlite.model.Product;
import com.example.sqlite.model.ProductType;

import java.util.ArrayList;

public class ProductTypeAdapter extends RecyclerView.Adapter<ProductTypeAdapter.ViewHolder> {

    private ArrayList<ProductType> listData;
    private Context context;
    private OnItemListener<ProductType> listener;

    public ProductTypeAdapter(ArrayList<ProductType> listData, Context context,OnItemListener<ProductType> listener) {
        this.listData = listData;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product_type, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindItem(listData.get(position));
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvId;
        private TextView tvName;
        private ImageView imgEdit;
        private ImageView imgDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvId = itemView.findViewById(R.id.tv_ma_loai);
            tvName = itemView.findViewById(R.id.tv_ten_loai);
            imgEdit = itemView.findViewById(R.id.img_edit);
            imgDelete = itemView.findViewById(R.id.img_delete);


        }

        @SuppressLint("SetTextI18n")
        public void bindItem(ProductType productType) {
            tvId.setText("Mã loại : " + productType.getTypeID());
            tvName.setText("Tên loại : " + productType.getTypeName());
            imgEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.editItem(productType);
                }
            });

            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.deleteItem(productType);
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClickItem(productType);
                }
            });
        }
    }

}
