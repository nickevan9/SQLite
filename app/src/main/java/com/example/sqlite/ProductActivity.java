package com.example.sqlite;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlite.adapter.OnItemListener;
import com.example.sqlite.adapter.ProductAdapter;
import com.example.sqlite.database.ProductSqlite;
import com.example.sqlite.model.Product;
import com.example.sqlite.model.ProductType;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ProductActivity extends AppCompatActivity implements OnItemListener<Product> {

    private ProductType productType;

    private TextView tvHeader;
    private EditText edId, edName, edPrice, edDate, edCount;
    private Button btnAdd, btnEdit;
    private RecyclerView rvProduct;

    private ProductAdapter productAdapter;

    private ArrayList<Product> productArrayList = new ArrayList<>();

    private ProductSqlite productSqlite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        productType = (ProductType) getIntent().getSerializableExtra("ProductType");

        tvHeader = findViewById(R.id.tvHeader);
        edId = findViewById(R.id.ed_id);
        edName = findViewById(R.id.ed_name);
        edPrice = findViewById(R.id.ed_price);
        edDate = findViewById(R.id.ed_date);
        edCount = findViewById(R.id.ed_count);

        rvProduct = findViewById(R.id.rv_product);
        btnAdd = findViewById(R.id.btn_add);
        btnEdit = findViewById(R.id.btn_edit);

        tvHeader.setText("Loại sản phẩm " + productType.getTypeName());

        productSqlite = new ProductSqlite(this);

        initRecyclerView();


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = edId.getText().toString();
                String name = edName.getText().toString();
                int price = Integer.parseInt(edPrice.getText().toString());
                String date = edDate.getText().toString();
                int count = Integer.parseInt(edCount.getText().toString());
                if (!name.isEmpty() && !id.isEmpty() && price != 0 && !date.isEmpty() && count != 0) {
                    boolean status = productSqlite.insertProduct(new Product(id, name, count, price, productType.getTypeID(), date));
                    if (status) {
                        Toast.makeText(ProductActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        getList();

                    } else {
                        Toast.makeText(ProductActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }

    private void initRecyclerView() {
        productAdapter = new ProductAdapter(productArrayList, this, this);

        rvProduct.setLayoutManager(new LinearLayoutManager(this));
        rvProduct.setHasFixedSize(true);
        rvProduct.setAdapter(productAdapter);

        getList();
    }


    public void showDialog(ProductType productType) {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_filter);

        EditText edStartDialog = (EditText) dialog.findViewById(R.id.ed_date_1);
        EditText edEndDialog = (EditText) dialog.findViewById(R.id.ed_date_2);

        Button btnCloseDialog = (Button) dialog.findViewById(R.id.btn_close);
        Button btnSortDialog = (Button) dialog.findViewById(R.id.btn_sort);
        btnCloseDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnSortDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edStartDialog.getText().toString();
                String end = edEndDialog.getText().toString();
                if (!name.isEmpty()) {
                    boolean status = productSqlite.updateProductType(new ProductType(productType.getTypeID(), name));
                    if (status) {
                        Toast.makeText(ProductActivity.this, "Edit Success", Toast.LENGTH_SHORT).show();
                        getList();
                        dialog.dismiss();

                    } else {
                        Toast.makeText(ProductActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }
            }
        });

        dialog.show();

    }

    private Date convertStringTodate(String dateFormat) {
        DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date date = sourceFormat.parse(dateFormat);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    private void getList() {
        productArrayList.clear();
        productArrayList.addAll(productSqlite.getAllProduct());
        productAdapter.notifyDataSetChanged();
    }

    @Override
    public void deleteItem(Product data) {
        productSqlite.deleteProduct(data);
    }

    @Override
    public void editItem(Product data) {

    }

    @Override
    public void onClickItem(Product data) {

    }
}