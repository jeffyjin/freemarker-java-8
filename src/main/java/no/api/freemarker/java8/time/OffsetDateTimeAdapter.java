/*
 * Copyright (c) 2015-2015 Amedia Utvikling AS.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package no.api.freemarker.java8.time;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.AdapterTemplateModel;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateScalarModel;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static no.api.freemarker.java8.time.DateTimeTools.*;

/**
 * OffsetDateTimeAdapter adds basic format support for {@link OffsetDateTime} too FreeMarker 2.3.23 and above.
 */
public class OffsetDateTimeAdapter extends AbstractAdapter<OffsetDateTime> implements AdapterTemplateModel,
        TemplateScalarModel, TemplateHashModel {

    public OffsetDateTimeAdapter(OffsetDateTime obj, BeansWrapper wrapper) {
        super(obj, wrapper);
    }


    @Override
    public TemplateModel getForType(String s) throws TemplateModelException {
        if (METHOD_FORMAT.equals(s)) {
            return new OffsetDateTimeFormatter(getObject());
        }
        throw new TemplateModelException(METHOD_UNKNOWN_MSG + s);
    }


    public class OffsetDateTimeFormatter extends AbstractFormatter<OffsetDateTime> implements TemplateMethodModelEx {

        public OffsetDateTimeFormatter(OffsetDateTime obj) {
            super(obj);
        }


        @Override
        public Object exec(List list) throws TemplateModelException {
            return getObject().format(createDateTimeFormatter(list, 0, DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        }
    }
}
