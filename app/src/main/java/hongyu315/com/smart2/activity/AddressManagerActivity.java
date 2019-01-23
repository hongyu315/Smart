package hongyu315.com.smart2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

import java.util.ArrayList;
import java.util.List;

import hongyu315.com.smart2.R;
import hongyu315.com.smart2.adapter.AddressAdapter;
import hongyu315.com.smart2.bean.Address;
import hongyu315.com.smart2.util.SysUtils;

public class AddressManagerActivity extends BaseActivity implements AddressAdapter.OnAddressItemClickListener {

    private TitleBar titleBar;
    private ListView listView;
    private AddressAdapter adapter;
    private List<Address> addressList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_manager);

        findViews();
        initData();
    }


    @Override
    protected void findViews() {
        super.findViews();

        titleBar = findViewById(R.id.address_activity_title_bar);
        listView = findViewById(R.id.address_list);
        adapter = new AddressAdapter(this,addressList, this);
        listView.setAdapter(adapter);

        titleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) {
                mFinish();
            }

            @Override
            public void onTitleClick(View v) {
            }

            @Override
            public void onRightClick(View v) {
            }
        });

    }

    @Override
    protected void initData() {
        super.initData();

        Address address = new Address();
        address.setDefault(true);
        address.setUserLocate("上海市泗泾镇");
        address.setUserDetailAddress("鼓浪路2399号");
        address.setUserName("张三");
        address.setUserPhone("138****2222");

        Address address1 = new Address();
        address1.setDefault(false);
        address.setUserLocate("上海市泗泾镇");
        address.setUserDetailAddress("鼓浪路2399号");
        address1.setUserName("张三");
        address1.setUserPhone("138****2222");

        addressList.add(address);
        addressList.add(address1);

        adapter.notifyDataSetChanged();
    }

    public void onAddNewAddressLayoutClick(View view){
        SysUtils.startActivity(this,AddAddressActivity.class);
    }

    @Override
    public void onDefaultAddressClick(View view, boolean isChecked) {
        int position = (int) view.getTag();

        //如果已经是默认地址的，则不能再次点击本身进行取消，只能通过设置其他地址为默认地址后生效；
        if (addressList.get(position).isDefault()){
            return;
        }

        for (Address address:addressList) {
           address.setDefault(false);
        }

        addressList.get(position).setDefault(isChecked);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onEditAddressClick(View view) {
        int position = (int) view.getTag();
        Intent intent = new Intent(AddressManagerActivity.this,AddAddressActivity.class);
        intent.putExtra("address",addressList.get(position));
        startActivity(intent);
    }

    @Override
    public void onDeleteAddressClick(View view) {
        int position = (int) view.getTag();
        addressList.remove(position);
        adapter.notifyDataSetChanged();
    }
}
