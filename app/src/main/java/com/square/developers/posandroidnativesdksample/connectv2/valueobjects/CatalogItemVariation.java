package com.square.developers.posandroidnativesdksample.connectv2.valueobjects;

import java.util.List;

public class CatalogItemVariation extends Base {
  public String item_id;

  public String name;

  public String sku;

  public String upc;

  public Integer ordinal;
  public String pricing_type;

  public Money price_money;

  public List<ItemVariationLocationOverrides> location_overrides;

  public Boolean track_inventory;
  public String inventory_alert_typel;

  public Long inventory_alert_threshold;

  public String user_data;

  public Long service_duration;
}
