package com.open.sina.finance.utils.constant;

/**
 * 常量
 */

public class Constants {

    public static final String AD_ID = "ad_id";
    public static final String TRADE_TYPE = "trade_type";
    public static final String TRADE_ID = "trade_id";
    public static final String TRADE_PRICE = "trade_price";

    public static final String TRADEDETAILSJSON = "TradeDetailsJson";

    /*交易类型*/
    public static final String ONLINEBUYAD = "ONLINEBUYAD";//线上购买广告
    public static final String ONLINESELLAD = "ONLINESELLAD";//线上出售广告
    public static final String OFFLINESELLAD = "OFFLINESELLAD";//线下出售广告
    public static final String OFFLINEBUYAD = "OFFLINEBUYAD";//线下购买广告

    /*支付方式*/
    public static final String ALIPAY = "ALIPAY";
    public static final String WEIXIN = "WEIXIN";
    public static final String UNIONPAY = "UNIONPAY";

    /*交易状态*/
    public static final String PAY_MONEY = "PAY_MONEY";//等待付款
    public static final String PAY_COIN = "PAY_COIN";//付款完成，等待放币
    public static final String TRADE_SUCCESS = "TRADE_SUCCESS";//已放币，交易完成
    public static final String TRADE_CANCEL_BUY = "TRADE_CANCEL_BUY";//买方取消交易
    public static final String TRADE_ARBITRATION = "TRADE_ARBITRATION";//交易仲裁中
    public static final String TRADE_ARBITRATION_SELL = "TRADE_ARBITRATION_SELL";//仲裁结束，卖方获胜
    public static final String TRADE_ARBITRATION_BUY = "TRADE_ARBITRATION_BUY";//仲裁结束，买方获胜

    /*仲裁状态*/
    public static final String ARBITRATION_WORK = "ARBITRATION_WORK";//仲裁中
    public static final String ARBITRATION_SUCCESS_SELL = "ARBITRATION_SUCCESS_SELL";//仲裁结束，卖方获胜
    public static final String ARBITRATION_SUCCESS_BUY = "ARBITRATION_SUCCESS_BUY";//仲裁结束，买方获胜

    /*评价*/
    public static final String GOOD_EVALUATION = "GOOD_EVALUATION";//好评
    public static final String MIDDLE_EVALUATION = "MIDDLE_EVALUATION";//中评
    public static final String BAD_EVALUATION = "BAD_EVALUATION";//差评

    /*手续费类型*/
    public static final String TRADE_BUYER_FEE = "TRADE_BUYER_FEE";//买家交易费
    public static final String TRADE_SELLER_FEE = "TRADE_SELLER_FEE";//卖家交易费
    public static final String COIN_OUT_FEE = "COIN_OUT_FEE";//提币手续费
    public static final String ACC_IN_FEE = "ACC_IN_FEE";//充值手续费
    public static final String AD_BUY_FEE = "AD_BUY_FEE";//购买广告手续费
    public static final String AD_SELL_FEE = "AD_SELL_FEE";//卖出广告手续费

    /*消息类型*/
    public static final String TRADE = "TRADE";//交易
    public static final String ARBITRATION = "ARBITRATION";//仲裁

    /*广告状态*/
    public static final String AD_RELEASE = "AD_RELEASE";//上架
    public static final String AD_CANCEL = "AD_CANCEL";//下架
    public static final String AD_FREEZE = "AD_FREEZE";//冻结
}
