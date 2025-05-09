package com.comitfy.kidefy.util.common;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Service;

@Service
public class RateLimiterService {

  private final Cache<UUID, Long> requestCache = CacheBuilder.newBuilder()
      .expireAfterWrite(60, TimeUnit.SECONDS)
      .build();

  private final Cache<String, Long> requestCacheBarcodeFromDesktop = CacheBuilder.newBuilder()
          .expireAfterWrite(10, TimeUnit.SECONDS)
          .build();

  public boolean isAllowed(UUID key) {
    Long lastRequestTime = requestCache.getIfPresent(key);
    long currentTime = System.currentTimeMillis();

    if (lastRequestTime == null || (currentTime - lastRequestTime) >= 60000) {
      requestCache.put(key, currentTime);
      return true;
    }
    return false;
  }

  public void isAllowedClearCache(UUID key) {
      requestCache.invalidate(key);
  }

  public boolean isAllowedBarcodeForDesktop(UUID key, String barcodeNumber) {
    Long lastRequestTime = requestCacheBarcodeFromDesktop.getIfPresent(key.toString()+barcodeNumber);
    long currentTime = System.currentTimeMillis();

    if (lastRequestTime == null || (currentTime - lastRequestTime) >= 10000) {
      requestCacheBarcodeFromDesktop.put(key+barcodeNumber, currentTime);
      return true;
    }
    return false;
  }
}