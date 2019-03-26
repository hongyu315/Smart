package com.djs.one.view.pickdatetime.adapter.datetime;

import android.support.annotation.NonNull;

import com.djs.one.view.pickdatetime.bean.DateParams;
import com.djs.one.view.pickdatetime.bean.DatePick;


/**
 * Created by fhf11991 on 2017/8/29.
 */

public class MonthAdapter extends DatePickAdapter {

    public MonthAdapter(@NonNull DateParams dateParams, @NonNull DatePick datePick) {
        super(dateParams, datePick);
    }

    @Override
    public int getCurrentIndex() {
        return mData.indexOf(mDatePick.month);
    }

    @Override
    public void refreshValues() {
        setData(getArray(12));
    }
}
