// Copyright (c) 2014, Facebook, Inc.  All rights reserved.
// This source code is licensed under the BSD-style license found in the
// LICENSE file in the root directory of this source tree. An additional grant
// of patent rights can be found in the PATENTS file in the same directory.

package org.rocksdb.test;

import org.junit.AfterClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.rocksdb.*;

import static org.assertj.core.api.Assertions.assertThat;

public class BlockBasedTableConfigTest {

  @ClassRule
  public static final RocksMemoryResource rocksMemoryResource =
      new RocksMemoryResource();

  @AfterClass
  public static void printMessage(){
    System.out.println("Passed BlockBasedTableConfigTst.");
  }

  @Test
  public void shouldTestBlockBasedTableConfig() {
    BlockBasedTableConfig blockBasedTableConfig =
        new BlockBasedTableConfig();
    blockBasedTableConfig.setNoBlockCache(true);
    assertThat(blockBasedTableConfig.noBlockCache()).isTrue();
    blockBasedTableConfig.setBlockCacheSize(8 * 1024);
    assertThat(blockBasedTableConfig.blockCacheSize()).
        isEqualTo(8 * 1024);
    blockBasedTableConfig.setBlockSizeDeviation(12);
    assertThat(blockBasedTableConfig.blockSizeDeviation()).
        isEqualTo(12);
    blockBasedTableConfig.setBlockRestartInterval(15);
    assertThat(blockBasedTableConfig.blockRestartInterval()).
        isEqualTo(15);
    blockBasedTableConfig.setWholeKeyFiltering(false);
    assertThat(blockBasedTableConfig.wholeKeyFiltering()).
        isFalse();
    blockBasedTableConfig.setCacheIndexAndFilterBlocks(true);
    assertThat(blockBasedTableConfig.cacheIndexAndFilterBlocks()).
        isTrue();
    blockBasedTableConfig.setHashIndexAllowCollision(false);
    assertThat(blockBasedTableConfig.hashIndexAllowCollision()).
        isFalse();
    blockBasedTableConfig.setBlockCacheCompressedSize(40);
    assertThat(blockBasedTableConfig.blockCacheCompressedSize()).
        isEqualTo(40);
    blockBasedTableConfig.setChecksumType(ChecksumType.kNoChecksum);
    blockBasedTableConfig.setChecksumType(ChecksumType.kxxHash);
    assertThat(blockBasedTableConfig.checksumType().equals(
        ChecksumType.kxxHash));
    blockBasedTableConfig.setIndexType(IndexType.kHashSearch);
    assertThat(blockBasedTableConfig.indexType().equals(
        IndexType.kHashSearch));
    blockBasedTableConfig.setBlockCacheCompressedNumShardBits(4);
    assertThat(blockBasedTableConfig.blockCacheCompressedNumShardBits()).
        isEqualTo(4);
    blockBasedTableConfig.setCacheNumShardBits(5);
    assertThat(blockBasedTableConfig.cacheNumShardBits()).
        isEqualTo(5);
    blockBasedTableConfig.setBlockSize(10);
    assertThat(blockBasedTableConfig.blockSize()).isEqualTo(10);
    System.out.println("Passed BlockBasedTableConfigTest.");
  }

  @Test
  public void shouldTestBlockBasedTableWithFilter() {
    Options options = new Options();
    options.setTableFormatConfig(
        new BlockBasedTableConfig().setFilter(
            new BloomFilter(10)));
    assertThat(options.tableFactoryName()).
        isEqualTo("BlockBasedTable");
  }

  @Test
  public void shouldTestBlockBasedTableWithoutFilter() {
    Options options = new Options();
    options.setTableFormatConfig(
        new BlockBasedTableConfig().setFilter(null));
    assertThat(options.tableFactoryName()).
        isEqualTo("BlockBasedTable");
  }
}
