package com.example.sqlite;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlite.adapter.OnItemListener;
import com.example.sqlite.adapter.ProductTypeAdapter;
import com.example.sqlite.database.ProductSqlite;
import com.example.sqlite.model.ProductType;

import java.util.ArrayList;

public class ProductTypeActivity extends AppCompatActivity implements OnItemListener<ProductType> {

    private RecyclerView rvType;
    private Button btnAdd, btnEdit;

    private EditText edId, edName;
    private ProductTypeAdapter productTypeAdapter;

    private ArrayList<ProductType> productTypeArrayList = new ArrayList<>();

    private ProductSqlite productSqlite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_type);

        rvType = findViewById(R.id.rv_the_loai);
        btnAdd = findViewById(R.id.btn_add);
        btnEdit = findViewById(R.id.btn_edit);
        edId = findViewById(R.id.ed_id);
        edName = findViewById(R.id.ed_name);


        productSqlite = new ProductSqlite(this);

        initRecyclerView();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = edId.getText().toString();
                String name = edName.getText().toString();
                if (!name.isEmpty() && !id.isEmpty()) {
                    boolean status = productSqlite.insertProductType(new ProductType(id, name));
                    if (status) {
                        Toast.makeText(ProductTypeActivity.this, "Add Success", Toast.LENGTH_SHORT).show();
                        getList();

                    } else {
                        Toast.makeText(ProductTypeActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }

    public void showDialog(ProductType productType) {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_edit_product_type);

        EditText edNameDialog = (EditText) dialog.findViewById(R.id.ed_name);

        Button btnCloseDialog = (Button) dialog.findViewById(R.id.btn_close);
        Button btnEditDialog = (Button) dialog.findViewById(R.id.btn_edit);
        btnCloseDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnEditDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edNameDialog.getText().toString();
                if (!name.isEmpty()) {
                    boolean status = productSqlite.updateProductType(new ProductType(productType.getTypeID(), name));
                    if (status) {
                        Toast.makeText(ProductTypeActivity.this, "Edit Success", Toast.LENGTH_SHORT).show();
                        getList();
                        dialog.dismiss();

                    } else {
                        Toast.makeText(ProductTypeActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }
            }
        });

        dialog.show();

    }

    private void initRecyclerView() {
        productTypeAdapter = new ProductTypeAdapter(productTypeArrayList, this, this);

        rvType.setLayoutManager(new LinearLayoutManager(this));
        rvType.setHasFixedSize(true);
        rvType.setAdapter(productTypeAdapter);

        getList();
    }

    private void getList() {
        productTypeArrayList.clear();
        productTypeArrayList.addAll(productSqlite.getAllProductType());
        productTypeAdapter.notifyDataSetChanged();
    }

    @Override
    public void deleteItem(ProductType data) {
        productSqlite.deleteProductType(data);
        getList();
    }

    @Override
    public void editItem(ProductType data) {
        showDialog(data);

    }

    @Override
    public void onClickItem(ProductType data) {
        Intent intent = new Intent(this, ProductActivity.class);
        intent.putExtra("ProductType", data);
        startActivity(intent);
    }
}