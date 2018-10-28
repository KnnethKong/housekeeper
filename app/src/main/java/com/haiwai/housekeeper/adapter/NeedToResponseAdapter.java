package com.haiwai.housekeeper.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.Response;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.user.EvaluateActivity;
import com.haiwai.housekeeper.activity.user.GoPayActivity;
import com.haiwai.housekeeper.activity.user.TakeOrderServeListActivity;
import com.haiwai.housekeeper.activity.user.WatchEvaluateActivity;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.NeedResponseEntity;
import com.haiwai.housekeeper.fragment.user.need.ToResponseFragment;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.utils.ActivityTools;
import com.haiwai.housekeeper.utils.AssetsUtils;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.TimeUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.widget.CustomDialog;
import com.haiwai.housekeeper.widget.RegisterDialog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ihope007 on 2016/9/2.
 */
public class NeedToResponseAdapter extends BaseAdapter {
    private Context context;
    private List<NeedResponseEntity.DataBean> list;
    private int needtype;
    private String isZhorEn = "";
    public NeedToResponseAdapter(Context context, List<NeedResponseEntity.DataBean> list, int needtype) {
        this.context = context;
        this.list = list;
        this.needtype = needtype;
        isZhorEn = AppGlobal.getInstance().getLagStr();
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder vh = ViewHolder.get(context, parent, R.layout.user_item_need_toresponse, position, convertView);
        TextView tvOrderNum = vh.getview(R.id.item_need_response_tvordernum);
        tvOrderNum.setTypeface(Typeface.MONOSPACE, Typeface.ITALIC);
        vh.setTextview(R.id.item_need_response_tv_date, TimeUtils.getDate2(list.get(position).getCtime()));
        vh.setTextview(R.id.item_need_response_tv_type, AssetsUtils.getSkillName(list.get(position).getType(),isZhorEn));
        tvOrderNum.setText(context.getResources().getString(R.string.main_need_order_num)+"\t" + list.get(position).getOrder_id());

        TextView tvserve = vh.getview(R.id.item_need_response_serve);
        if (1 == needtype) {
            tvserve.setVisibility(View.INVISIBLE);
        } else {
            tvserve.setVisibility(View.VISIBLE);
            if (!TextUtils.isEmpty(list.get(position).getNickname())) {
                tvserve.setText(list.get(position).getNickname());
            }
        }

        if (!"".equals(list.get(position).getAddress_info()))
            vh.setTextview(R.id.item_need_response_tvaddr, list.get(position).getAddress_info());
        final int statics = Integer.valueOf(list.get(position).getStaticX());
        TextView tvcancel = vh.getview(R.id.item_need_response_tvcancel);
        TextView tvchoose = vh.getview(R.id.item_need_response_tvchoose);
        if ("1".equals(list.get(position).getIs_ypin())) {
            tvcancel.setVisibility(View.INVISIBLE);
            tvchoose.setVisibility(View.VISIBLE);
            tvchoose.setText(context.getResources().getString(R.string.main_need_watch_order));
            tvchoose.setBackgroundResource(R.drawable.bg_shape_tv_rect_theme_fill_corner);
        } else if (0 == statics || 1 == statics) {
            tvcancel.setVisibility(View.VISIBLE);
            tvchoose.setVisibility(View.VISIBLE);
            tvcancel.setText(context.getResources().getString(R.string.main_need_cancel_order));
            tvchoose.setText(list.get(position).getJ_num() + context.getString(R.string.main_need_gqd));
        } else if (statics >= 2 && statics <= 8) {
            tvcancel.setVisibility(View.INVISIBLE);
            tvchoose.setVisibility(View.VISIBLE);
            if (statics == 2) {
                tvchoose.setText(context.getResources().getString(R.string.main_need_cancel_order));
                tvchoose.setBackgroundResource(R.drawable.bg_shape_tv_rect_gray_light_fill_corner);
                tvchoose.setTextColor(Color.parseColor("#6A6F78"));
            } else if (statics == 3) {
                tvcancel.setVisibility(View.VISIBLE);
                tvchoose.setVisibility(View.VISIBLE);
                tvcancel.setText(context.getResources().getString(R.string.main_need_agree_order));
                tvchoose.setText(context.getResources().getString(R.string.main_need_disagree_order));
            } else if (statics == 4) {
                tvcancel.setVisibility(View.INVISIBLE);
                tvchoose.setVisibility(View.VISIBLE);
                tvchoose.setText(context.getResources().getString(R.string.main_need_disagree_order));
            } else if (statics == 5) {
                tvcancel.setVisibility(View.INVISIBLE);
                tvchoose.setVisibility(View.VISIBLE);
                tvchoose.setText(context.getResources().getString(R.string.main_need_service));
            } else if (statics == 6 || statics == 7) {
                tvcancel.setVisibility(View.INVISIBLE);
                tvchoose.setVisibility(View.VISIBLE);
                tvchoose.setText(context.getResources().getString(R.string.main_need_pay_order));
            } else if (statics == 8) {
                tvcancel.setVisibility(View.INVISIBLE);
                tvchoose.setVisibility(View.VISIBLE);
                tvchoose.setText(context.getResources().getString(R.string.main_need_evaluate_order));
            }
        } else if (statics == 9 || statics == 10) {
            tvcancel.setVisibility(View.INVISIBLE);
            tvchoose.setVisibility(View.VISIBLE);
            tvchoose.setText(context.getResources().getString(R.string.main_need_has_canceld));
            tvchoose.setBackground(null);
        }
        tvcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (0 == statics || 1 == statics) {
                    CustomDialog.Builder customBuilder = new CustomDialog.Builder(context);
                    customBuilder.setMessage(context.getResources().getString(R.string.main_need_is_cancel)).setPositiveButton(context.getResources().getString(R.string.message_alert_yes),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    requestCancelOrder(list.get(position).getId(), list.get(position).getType(), 1);
                                    dialog.dismiss();
                                }
                            })
                            .setNegativeButton(context.getResources().getString(R.string.message_alert_no),
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    })
                            .create().show();
//                    context.showCancelDialog(list.get(position).getId(), list.get(position).getType());
                } else if (statics == 3 || statics == 4) {
                    //同意报销
                    CustomDialog.Builder customBuilder = new CustomDialog.Builder(context);
                    customBuilder.setMessage(context.getResources().getString(R.string.main_need_is_agree)).setPositiveButton(context.getResources().getString(R.string.message_alert_yes),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    requestAgreeOrder(list.get(position).getId(), 2);
                                    dialog.dismiss();
                                }
                            })
                            .setNegativeButton(context.getResources().getString(R.string.message_alert_no),
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    })
                            .create().show();
                }
            }
        });
        tvchoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("1".equals(list.get(position).getIs_ypin())) {
                    //tiaozhuandaopingjia
                    Bundle bundle=new Bundle();
                    bundle.putBoolean("fromO2O",true);
                    bundle.putString("uid",list.get(position).getJ_uid());
                    bundle.putString("oid",list.get(position).getId());
                    ActivityTools.goNextActivity(context, WatchEvaluateActivity.class,bundle);
                } else {
                    if (statics == 0 || statics == 1) {
                        Bundle bundle = new Bundle();
                        bundle.putString("id", list.get(position).getId());
                        bundle.putString("lat", list.get(position).getLat());
                        bundle.putString("long", list.get(position).getLongX());
                        bundle.putString("type", list.get(position).getType());
                        bundle.putString("addr", list.get(position).getAddress_info());
                        bundle.putString("date", list.get(position).getCtime());
                        ActivityTools.goNextActivity(context, TakeOrderServeListActivity.class, bundle);
                    } else if (statics == 2) {
                        CustomDialog.Builder customBuilder = new CustomDialog.Builder(context);
                        customBuilder.setMessage(context.getResources().getString(R.string.main_need_is_cancel)).setPositiveButton(context.getResources().getString(R.string.message_alert_yes),
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        requestCancelOrder(list.get(position).getId(), list.get(position).getType(), 2);
                                        dialog.dismiss();
                                    }
                                })
                                .setNegativeButton(context.getResources().getString(R.string.message_alert_no),
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        })
                                .create().show();
                    } else if (statics == 3 || statics == 4) {//驳回
                        CustomDialog.Builder customBuilder = new CustomDialog.Builder(context);
                        customBuilder.setMessage(context.getResources().getString(R.string.main_need_is_disagree)).setPositiveButton(context.getResources().getString(R.string.message_alert_yes),
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        requestDisAgreeOrder(list.get(position).getId(), 2);
                                        dialog.dismiss();
                                    }
                                })
                                .setNegativeButton(context.getResources().getString(R.string.message_alert_no),
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        })
                                .create().show();
                    } else if (statics == 6 || statics == 7) {//支付
//                        CustomDialog.Builder customBuilder = new CustomDialog.Builder(context);
//                        customBuilder.setMessage("确定要支付吗？").setPositiveButton(context.getResources().getString(R.string.message_alert_yes),
//                                new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        requestPayOrder(list.get(position).getId());
//                                        dialog.dismiss();
//                                    }
//                                })
//                                .setNegativeButton(context.getResources().getString(R.string.message_alert_no),
//                                        new DialogInterface.OnClickListener() {
//                                            public void onClick(DialogInterface dialog, int which) {
//                                                dialog.dismiss();
//                                            }
//                                        })
//                                .create().show();
                        Bundle ev_bundle = new Bundle();
                        ev_bundle.putString("oid", list.get(position).getId());
                        ev_bundle.putString("j_uid", list.get(position).getJ_uid());
                        ActivityTools.goNextActivity(context, GoPayActivity.class,ev_bundle);
//                        ToastUtil.shortToast(context,"支付");

                    } else if (statics == 8) {//评价
                        Bundle ev_bundle = new Bundle();
                        ev_bundle.putString("oid", list.get(position).getId());
                        ev_bundle.putString("uid", list.get(position).getJ_uid());
                        ev_bundle.putString("type", list.get(position).getType());
                        ActivityTools.goNextActivity(context, EvaluateActivity.class, ev_bundle);
                    }
                }
            }
        });

        return vh.getContentView();
    }

    public void requestCancelOrder(String id, final String type, final int switchs) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", AppGlobal.getInstance().getUser().getUid());
        map.put("id", id);
        map.put("type", type);
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(context, Contants.order_ydel, map, null, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    int code = JsonUtils.getJsonInt(response, "status");
                    if (code == 200) {
                        final RegisterDialog dialog = new RegisterDialog(context, context.getResources().getString(R.string.message_alert), context.getResources().getString(R.string.commit_success));
                        dialog.show();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dialog.dismiss();
//                                mPullListView.doPullRefreshing(true,500);
                                if (1 == switchs) {
                                    Intent intent = new Intent();
                                    intent.setAction("refresh_need");
                                    intent.putExtra("need", "1");
                                    context.sendBroadcast(intent);
                                } else if (2 == switchs) {
                                    Intent intent = new Intent();
                                    intent.setAction("refresh_need2");
                                    intent.putExtra("need2", "1");
                                    context.sendBroadcast(intent);
                                }
                            }
                        }, 1500);
                    } else {
                        ToastUtil.shortToast(context, ErrorCodeUtils.getRegisterError(code + ""));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));
    }

    public void requestAgreeOrder(String id, final int switchs) {//同意报价
        Map<String, String> map = new HashMap<>();
        map.put("uid", AppGlobal.getInstance().getUser().getUid());
        map.put("oid", id);
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(context, Contants.offer_saveque, map, null, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    int code = JsonUtils.getJsonInt(response, "status");
                    if (code == 200) {
                        final RegisterDialog dialog = new RegisterDialog(context, context.getResources().getString(R.string.message_alert), context.getResources().getString(R.string.commit_success));
                        dialog.show();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dialog.dismiss();
//                                mPullListView.doPullRefreshing(true,500);
                                if (1 == switchs) {
                                    Intent intent = new Intent();
                                    intent.setAction("refresh_need");
                                    intent.putExtra("need", "1");
                                    context.sendBroadcast(intent);
                                } else if (2 == switchs) {
                                    Intent intent = new Intent();
                                    intent.setAction("refresh_need2");
                                    intent.putExtra("need2", "1");
                                    context.sendBroadcast(intent);
                                }
                            }
                        }, 1500);
                    } else {
                        ToastUtil.shortToast(context, ErrorCodeUtils.getRegisterError(code + ""));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));
    }

    public void requestDisAgreeOrder(String id, final int switchs) {//驳回
        Map<String, String> map = new HashMap<>();
        map.put("uid", AppGlobal.getInstance().getUser().getUid());
        map.put("oid", id);
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(context, Contants.offer_bh, map, null, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    int code = JsonUtils.getJsonInt(response, "status");
                    if (code == 200) {
                        final RegisterDialog dialog = new RegisterDialog(context, context.getResources().getString(R.string.message_alert), context.getResources().getString(R.string.commit_success));
                        dialog.show();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dialog.dismiss();
//                                mPullListView.doPullRefreshing(true,500);
                                if (1 == switchs) {
                                    Intent intent = new Intent();
                                    intent.setAction("refresh_need");
                                    intent.putExtra("need", "1");
                                    context.sendBroadcast(intent);
                                } else if (2 == switchs) {
                                    Intent intent = new Intent();
                                    intent.setAction("refresh_need2");
                                    intent.putExtra("need2", "1");
                                    context.sendBroadcast(intent);
                                }
                            }
                        }, 1500);
                    } else {
                        ToastUtil.shortToast(context, ErrorCodeUtils.getRegisterError(code + ""));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));
    }

    public void requestPayOrder(String id) {
        Map<String, String> map = new HashMap<>();
        map.put("stripetoken", "dfdf");
        map.put("uid", AppGlobal.getInstance().getUser().getUid());
        map.put("oid", id);
        map.put("money", "50");
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(context, Contants.order_zhi, map, null, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    int code = JsonUtils.getJsonInt(response, "status");
                    if (code == 200) {
                        final RegisterDialog dialog = new RegisterDialog(context,context.getResources().getString(R.string.message_alert), context.getResources().getString(R.string.commit_success));
                        dialog.show();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dialog.dismiss();
//                                mPullListView.doPullRefreshing(true,500);
                                Intent intent = new Intent();
                                intent.setAction("refresh_need2");
                                intent.putExtra("need2", "1");
                                context.sendBroadcast(intent);
                            }
                        }, 1500);
                    } else {
                        ToastUtil.shortToast(context, ErrorCodeUtils.getRegisterError(code + ""));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));
    }
}
