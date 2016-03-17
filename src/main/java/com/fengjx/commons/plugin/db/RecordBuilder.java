/**
 * Copyright (c) 2011-2016, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.fengjx.commons.plugin.db;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.*;
import java.util.Map;

/**
 * ModelBuilder.
 */
public class RecordBuilder {

    @SuppressWarnings({
            "rawtypes", "unchecked"
    })
    public static <T extends Record> T build(ResultSet rs, Class<T> modelClass)
            throws SQLException, InstantiationException, IllegalAccessException {
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        String[] labelNames = new String[columnCount + 1];
        int[] types = new int[columnCount + 1];
        buildLabelNamesAndTypes(rsmd, labelNames, types);
        T ar = modelClass.newInstance();
        Map<String, Object> attrs = ar._getColumns();
        for (int i = 1; i <= columnCount; i++) {
            Object value;
            if (types[i] < Types.BLOB) {
                value = rs.getObject(i);
            } else if (types[i] == Types.CLOB) {
                value = handleClob(rs.getClob(i));
            } else if (types[i] == Types.NCLOB) {
                value = handleClob(rs.getNClob(i));
            } else if (types[i] == Types.BLOB) {
                value = handleBlob(rs.getBlob(i));
            } else {
                value = rs.getObject(i);
            }
            attrs.put(labelNames[i], value);
        }
        return ar;
    }

    private static void buildLabelNamesAndTypes(ResultSetMetaData rsmd, String[] labelNames,
            int[] types) throws SQLException {
        for (int i = 1; i < labelNames.length; i++) {
            labelNames[i] = rsmd.getColumnLabel(i);
            types[i] = rsmd.getColumnType(i);
        }
    }

    public static byte[] handleBlob(Blob blob) throws SQLException {
        if (blob == null) {
            return null;
        }
        InputStream is = null;
        try {
            is = blob.getBinaryStream();
            if (is == null) {
                return null;
            }
            if ((int) blob.length() == 0) {
                return null;
            }
            return IOUtils.toByteArray(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(is);
        }
    }

    public static String handleClob(Clob clob) throws SQLException {
        if (clob == null) {
            return null;
        }
        Reader reader = null;
        try {
            reader = clob.getCharacterStream();
            if (reader == null) {
                return null;
            }
            if ((int) clob.length() == 0) {
                return null;
            }
            return IOUtils.toString(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(reader);
        }
    }
}
