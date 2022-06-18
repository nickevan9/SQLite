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

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private ArrayList<Product> listData;
    private Context context;
    private OnItemListener<Product> listener;

    public ProductAdapter(ArrayList<Product> listData, Context context, OnItemListener<Product> listener) {
        this.listData = listData;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new ProductAdapter.ViewHolder(view);
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
        private TextView tvName;
        private TextView tvCount;
        private TextView tvPrice;
        private TextView tvDate;
        private ImageView imgEdit;
        private ImageView imgDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_ten_san_pham);
            tvCount = itemView.findViewById(R.id.tv_so_luong);
            tvPrice = itemView.findViewById(R.id.tv_don_gia);
            tvDate = itemView.findViewById(R.id.tv_date);
            imgEdit = itemView.findViewById(R.id.img_edit);
            imgDelete = itemView.findViewById(R.id.img_delete);
        }

        @SuppressLint("SetTextI18n")
        public void bindItem(Product product) {

            tvName.setText("Tên sản phẩm : " + product.getProductName());
            tvCount.setText("Số lượng : " + product.getCount());
            tvPrice.setText("Đơn giá : " + convertInt(product.getPrice()));
            tvDate.setText("Nhập kho : " + product.getDate());

            imgEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.editItem(product);
                }
            });

            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.deleteItem(product);
                }
            });
        }

        private String convertDate(Long date) {
            Date dateConvert = new Date(date);
            SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yyyy");
            String dateText = df2.format(date);
            return dateText;
        }

        private String convertInt(int price) {
            NumberFormat formatter = new DecimalFormat("#,###");
            String formattedNumber = formatter.format(price);
            return formattedNumber + " VNĐ";
        }
    }

}
