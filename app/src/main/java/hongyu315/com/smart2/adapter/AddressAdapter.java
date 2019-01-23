package hongyu315.com.smart2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import hongyu315.com.smart2.R;
import hongyu315.com.smart2.bean.Address;

/**
 * <p>文件描述：<p>
 * <p>作者：rain<p>
 * <p>创建时间：2019/1/12<p>
 * <p>更改时间：2019/1/12<p>
 * <p>版本号：1<p>
 */
public class AddressAdapter extends BaseAdapter implements View.OnClickListener {


    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<Address> addressList;
    private Address address;
    private OnAddressItemClickListener mListener;

    public AddressAdapter(Context context, List<Address> addresses, OnAddressItemClickListener listener){
        mContext = context;
        addressList = addresses;
        mLayoutInflater = LayoutInflater.from(context);
        mListener = listener;
    }

    @Override
    public int getCount() {
        return addressList.size();
    }

    @Override
    public Object getItem(int position) {
        return addressList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.address_item,null);

            viewHolder.userName = convertView.findViewById(R.id.address_user_name);
            viewHolder.userPhone = convertView.findViewById(R.id.address_user_phone);
            viewHolder.userAddress = convertView.findViewById(R.id.address_user_address);
            viewHolder.isDefaultAddress = convertView.findViewById(R.id.checkbox_address);
            viewHolder.editAddress = convertView.findViewById(R.id.edit_address);
            viewHolder.deleteAddress = convertView.findViewById(R.id.delete_address);
            viewHolder.checkboxLayout = convertView.findViewById(R.id.address_check_layout);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        address = addressList.get(position);

        viewHolder.userName.setText(address.getUserName());
        viewHolder.userPhone.setText(address.getUserPhone());
        viewHolder.userAddress.setText(address.getUserLocate() + address.getUserDetailAddress());
        viewHolder.isDefaultAddress.setChecked(address.isDefault());

        viewHolder.editAddress.setTag(position);
        viewHolder.deleteAddress.setTag(position);
        viewHolder.isDefaultAddress.setTag(position);
        viewHolder.checkboxLayout.setTag(position);
        viewHolder.isDefaultAddress.setClickable(false);

        viewHolder.checkboxLayout.setOnClickListener(this);
        viewHolder.editAddress.setOnClickListener(this);
        viewHolder.deleteAddress.setOnClickListener(this);

        return convertView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.address_check_layout:
                mListener.onDefaultAddressClick(v,true);
                break;
            case R.id.edit_address:
                mListener.onEditAddressClick(v);
                break;
            case R.id.delete_address:
                mListener.onDeleteAddressClick(v);
                break;
            default:
                break;
        }
    }

    public interface OnAddressItemClickListener{
        void onDefaultAddressClick(View view, boolean isChecked);
        void onEditAddressClick(View view);
        void onDeleteAddressClick(View view);
    }

    public class ViewHolder{
        TextView userName;
        TextView userPhone;
        TextView userAddress;

        LinearLayout checkboxLayout;
        CheckBox isDefaultAddress;
        TextView editAddress;
        TextView deleteAddress;
    }

}
