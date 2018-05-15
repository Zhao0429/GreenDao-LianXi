package com.example.administrator.greendaolianxi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.anonymous.greendao.gen.DaoMaster;
import com.example.anonymous.greendao.gen.DaoSession;
import com.example.anonymous.greendao.gen.UserDao;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @BindView(R.id.ed_name)
    EditText edName;
    @BindView(R.id.ed_page)
    EditText edPage;
    @BindView(R.id.add)
    Button add;
    @BindView(R.id.query)
    Button query;
    @BindView(R.id.delete)
    Button delete;
    @BindView(R.id.up)
    Button up;
    @BindView(R.id.recy)
    RecyclerView recy;
    @BindView(R.id.delete_id)
    EditText deleteId;
    private DaoMaster.DevOpenHelper devOpenHelper;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private UserDao userDao;
    private List<User> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        recy.setLayoutManager(new LinearLayoutManager(this));
        devOpenHelper = new DaoMaster.DevOpenHelper(this, "user.db", null);
        daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        daoSession = daoMaster.newSession();
        userDao = daoSession.getUserDao();

    }


    @OnClick({R.id.add, R.id.query, R.id.delete, R.id.up})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add:
                addDate();
                break;
            case R.id.query:
                queryDate();
                break;
            case R.id.delete:
                deleteDate();
                break;
            case R.id.up:
                upDate();
                break;
        }
    }

    private void addDate() {
        String name = edName.getText().toString();
        String page = edPage.getText().toString();
        User user = new User(null, name, page);
        long insert = userDao.insert(user);
        Toast.makeText(this, "添加成功", Toast.LENGTH_LONG).show();
        edName.setText("");
        edPage.setText("");
        queryDate();
    }

    private void queryDate() {

        list = userDao.loadAll();
        MyAdapter myAdapter = new MyAdapter(this, list);
        recy.setAdapter(myAdapter);

    }

    private void deleteDate() {

        String id = deleteId.getText().toString().trim();
        userDao.queryBuilder().where(UserDao.Properties.Id.eq(id)).buildDelete().executeDeleteWithoutDetachingEntities();

        deleteId.setText("");
        queryDate();
    }

    private void upDate() {
        String name = edName.getText().toString();
        User user = userDao.queryBuilder().where(UserDao.Properties.Name.eq(name)).build().unique();
        if (user!=null){
            String s = edName.getText().toString();
            user.setName(s);

        }
        Toast.makeText(this,"修改成功",Toast.LENGTH_SHORT).show();
        queryDate();
    }

}
