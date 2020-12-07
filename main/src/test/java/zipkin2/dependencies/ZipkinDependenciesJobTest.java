/*
 * Copyright 2016-2020 The OpenZipkin Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package zipkin2.dependencies;

import java.util.Date;
import java.util.TimeZone;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ZipkinDependenciesJobTest {
  @Test void parseDate() {
    // Date assertions don't assume UTC
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

    long date = ZipkinDependenciesJob.parseDay("2013-05-15");
    assertThat(new Date(date))
      .hasYear(2013)
      .hasMonth(5)
      .hasDayOfMonth(15)
      .hasHourOfDay(0)
      .hasMinute(0)
      .hasSecond(0)
      .hasMillisecond(0);
  }

  @Test void parseDate_malformed() {
    assertThatThrownBy(() -> ZipkinDependenciesJob.parseDay("2013/05/15"))
      .isInstanceOf(IllegalArgumentException.class);
  }
}
