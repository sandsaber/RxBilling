# RxBilling

Reactive wrapper around the Android Billing API that makes in app purchases and subscriptions really easy to handle. I've been using this library in my [apps](https://play.google.com/store/apps/developer?id=Vanniktech) for months and it has been working nicely.

# Usage

The core functionality is provided via an interface:

```java
public interface RxBilling {
  Observable<InventoryInApp> queryInAppPurchases(String... skuIds);

  Observable<InventorySubscription> querySubscriptions(String... skuIds);

  Completable isBillingForInAppSupported();

  Completable isBillingForSubscriptionsSupported();

  Single<PurchaseResponse> purchase(Inventory inventory, String developerPayload);

  Observable<PurchasedInApp> getPurchasedInApps();

  Observable<PurchasedSubscription> getPurchasedSubscriptions();

  Single<Integer> consumePurchase(PurchasedInApp purchasedInApp);

  void destroy();

  @interface BillingResponse {
    int OK = 0;
    int USER_CANCELED = 1;
    int SERVICE_UNAVAILABLE = 2;
    int BILLING_UNAVAILABLE = 3;
    int ITEM_UNAVAILABLE = 4;
    int DEVELOPER_ERROR = 5;
    int ERROR = 6;
    int ITEM_ALREADY_OWNED = 7;
    int ITEM_NOT_OWNED = 8;
  }
}
```

The actual [interface](rxbilling/src/main/java/com/vanniktech/rxbilling/RxBilling.java) also contains documentation.

This library offers two implementations.

### Aidl implementation

```groovy
implementation 'com.vanniktech:rxbilling-aidl:0.4.0'
```

```java
class YourActivity extends Activity implements NaviComponent {
  private RxBilling rxBilling;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate();
    rxBilling = new RxBillingAidlV3(this);
  }

  @Override public void onDestroy() {
    super.onDestroy();
    rxBilling.destroy();
  }
}
```

**Note:** Currently the Activity needs to be a [`NaviComponent`](https://github.com/trello/navi/blob/2.x/navi/src/main/java/com/trello/navi2/NaviComponent.java). This is due to the fact that there's no 'Lifecycle' callback for `onActivityResult`.

### Google Play Billing Library implementation

```groovy
implementation 'com.vanniktech:rxbilling-google-play-library:0.4.0'
```

```java
class YourActivity extends Activity {
  private RxBilling rxBilling;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate();
    rxBilling = new RxBillingGooglePlayLibrary(this);
  }

  @Override public void onDestroy() {
    super.onDestroy();
    rxBilling.destroy();
  }
}
```

### Testing

There's also a dedicated testing artifact.

```groovy
implementation 'com.vanniktech:rxbilling-testing:0.4.0'
```

# License

Copyright (C) 2018 Vanniktech - Niklas Baudy

Licensed under the Apache License, Version 2.0
