package dataService.dataHelper.service;

/**
 * @ author Molloh
 * @ version 2016/11/6
 * @ description
 */
public interface DataFactory {

    public AccountDataHelper getAccountDataHelper();

    public MemberDataHelper getMemberDataHelper();

    public HotelDataHelper getHotelDataHelper();
    
    public HotelPromotionDataHelper getHotelPromotionDataHelper();
    
    public OrderDataHelper getOrderDataHelper();

    public MarketPromotionDataHelper getMarketPromotionDataHelper();
    
    public CreditDataHelper getCreditDataHelper();
}
