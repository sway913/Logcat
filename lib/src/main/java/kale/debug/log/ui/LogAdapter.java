package kale.debug.log.ui;

import java.util.List;
import java.util.Map;

import android.content.res.Resources;
import android.support.v4.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import kale.debug.log.R;
import kale.debug.log.model.LogBean;
import kale.debug.log.constant.Level;

/**
 * @author Kale
 * @date 2016/3/25
 */
public class LogAdapter extends BaseAdapter {

    public static final Map<Level, Integer> LEV_MAP = new ArrayMap<>();

    static {
        LEV_MAP.put(Level.VERBOSE, R.color.gray_light);
        LEV_MAP.put(Level.DEBUG, R.color.blue);
        LEV_MAP.put(Level.INFO, R.color.green);
        LEV_MAP.put(Level.WARN, R.color.yellow);
        LEV_MAP.put(Level.ERROR, R.color.red);
        LEV_MAP.put(Level.FATAL, R.color.red);
        LEV_MAP.put(Level.ASSERT, R.color.red);
    }

    private List<LogBean> data;

    private LayoutInflater inflate;

    private Resources resources;

    public LogAdapter(List<LogBean> data) {
        this.data = data;
    }

    public void setData(List<LogBean> data) {
        this.data = data;
    }

    public List<LogBean> getData() {
        return data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflate == null) {
            resources = parent.getContext().getResources();
            inflate = LayoutInflater.from(parent.getContext());
        }

        final ViewHolder holder;
        if (convertView == null) {
            convertView = inflate.inflate(R.layout.kale_log_item, parent, false);
            holder = new ViewHolder();
            holder.tag = (TextView) convertView.findViewById(R.id.tag_tv);
            holder.msg = (TextView) convertView.findViewById(R.id.msg_tv);
            holder.time = (TextView) convertView.findViewById(R.id.time_tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        LogBean log = data.get(position);

        holder.tag.setText(log.tag);
        holder.msg.setText(log.msg);

        holder.msg.setTextColor(resources.getColor(LEV_MAP.get(log.lev)));

        holder.time.setText(log.time);

        return convertView;
    }

    private class ViewHolder {

        TextView tag, msg, time;
    }
}
