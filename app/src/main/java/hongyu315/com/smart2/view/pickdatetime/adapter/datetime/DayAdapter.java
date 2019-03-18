package hongyu315.com.smart2.view.pickdatetime.adapter.datetime;

import android.support.annotation.NonNull;

import java.util.Calendar;

import hongyu315.com.smart2.view.pickdatetime.bean.DateParams;
import hongyu315.com.smart2.view.pickdatetime.bean.DatePick;

/**
 * Created by fhf11991 on 2017/8/29.
 */

public class DayAdapter extends DatePickAdapter {

    public DayAdapter(@NonNull DateParams dateParams, @NonNull DatePick datePick) {
        super(dateParams, datePick);
    }

    @Override
    public int getCurrentIndex() {
        return mData.indexOf(mDatePick.day);
    }

    @Override
    public void refreshValues() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(mDatePick.year, mDatePick.month - 1, 1);

        int day = mDatePick.day;
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        mDatePick.day = Math.min(day, maxDay);

        setData(getArray(maxDay));
    }
}
