package com.vanniktech.rxbilling;

import androidx.annotation.NonNull;

public interface Logger {
  void d(@NonNull String log);

  void w(@NonNull String log);

  void w(@NonNull Throwable throwable);

  void e(@NonNull String log);
}
