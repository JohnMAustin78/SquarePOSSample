package com.square.developers.posandroidnativesdksample.connectv2.valueobjects;

import java.util.ArrayList;
import java.util.List;

public class CatalogItemModifierListInfo extends Base {
  public String modifier_list_id;

  public List<CatalogModifierOverride> modifier_overrides;

  public Integer min_selected_modifiers;

  public Integer max_selected_modifiers;

  public Boolean enabled;
}
