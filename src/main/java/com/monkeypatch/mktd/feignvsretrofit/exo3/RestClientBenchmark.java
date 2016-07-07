/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.monkeypatch.mktd.feignvsretrofit.exo3;

import com.monkeypatch.mktd.feignvsretrofit.exo1.MonkeyApi;
import com.monkeypatch.mktd.feignvsretrofit.exo1.model.Page;
import org.openjdk.jmh.annotations.*;

import java.util.Collection;
import java.util.stream.IntStream;

import static com.monkeypatch.mktd.feignvsretrofit.exo3.Configuration.BASE_URL;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.openjdk.jmh.annotations.Mode.Throughput;

/**
 * @see <a href="http://openjdk.java.net/projects/code-tools/jmh/">JMH</a>
 *
 * DO NOT MODIFY THIS FILE !
 */
public class RestClientBenchmark {

    @State(Scope.Benchmark)
    public static class BenchmarkConfiguration {
        @Param({"1", "10", "100"})
        int nbPages;
        MonkeyApi api;

        @Setup(Level.Trial)
        public void getApi() {
            api = ApiFactory.buildMonkeyApi(BASE_URL);
        }
    }

    @Benchmark
    @Warmup(iterations = 5, time = 1, timeUnit = SECONDS)
    @Measurement(iterations = 5, time = 1, timeUnit = SECONDS)
    @BenchmarkMode(Throughput)
    @Fork(5)
    public long load_monkeys(BenchmarkConfiguration config) {
        return IntStream.range(0, config.nbPages)
                .parallel()
                .mapToObj(config.api::getMonkeys)
                .map(Page::getContent)
                .flatMap(Collection::stream)
                .count();
    }

}
