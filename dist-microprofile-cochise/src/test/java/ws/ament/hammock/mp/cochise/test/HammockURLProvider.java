/*
 * Copyright 2017 Hammock and its contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ws.ament.hammock.mp.cochise.test;

import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.arquillian.test.spi.enricher.resource.ResourceProvider;
import ws.ament.hammock.HammockRuntime;

import javax.enterprise.inject.spi.CDI;
import java.lang.annotation.Annotation;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class HammockURLProvider implements ResourceProvider {
    @Override
    public Object lookup(ArquillianResource arquillianResource, Annotation... annotations) {
        HammockRuntime hammockRuntime = CDI.current().select(HammockRuntime.class).get();
        try {
            return new URL(hammockRuntime.getMachineURL());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean canProvide(Class<?> type) {
        return type.isAssignableFrom(URL.class);
    }

}