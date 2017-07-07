/*
 * Copyright 2017 Workday, Inc.
 *
 * This software is available under the MIT license.
 * Please see the LICENSE.txt file in this project.
 */

package org.elasticsearch.index.store

import java.io.File

import org.apache.lucene.util.AESWriter

/**
  * Builder that creates a ChunkedOutputStream that wraps an AESWriterOutputStream.
  */
object AESChunkedOutputStreamBuilder {
  def build(directory: File, name: String, pageSize: Int,
            createAESWriter: (File, String, Int) => AESWriter): ChunkedOutputStream = {
    val writer: AESWriter = createAESWriter(directory, name, pageSize)
    new ChunkedOutputStream(new AESWriterOutputStream(writer), AESIndexOutput.WRITE_CHUNK_SIZE)
  }
}
