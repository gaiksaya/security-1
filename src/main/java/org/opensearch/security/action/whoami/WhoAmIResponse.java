/*
 * Copyright 2015-2018 _floragunn_ GmbH
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * Portions Copyright OpenSearch Contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.opensearch.security.action.whoami;

import java.io.IOException;

import org.opensearch.action.ActionResponse;
import org.opensearch.common.Strings;
import org.opensearch.common.io.stream.StreamInput;
import org.opensearch.common.io.stream.StreamOutput;
import org.opensearch.common.xcontent.ToXContent;
import org.opensearch.common.xcontent.XContentBuilder;

public class WhoAmIResponse extends ActionResponse implements ToXContent {
    
    private String dn;
    private boolean isAdmin;
    private boolean isAuthenticated;
    private boolean isNodeCertificateRequest;
    
    public WhoAmIResponse(String dn, boolean isAdmin, boolean isAuthenticated, boolean isNodeCertificateRequest) {
        this.dn = dn;
        this.isAdmin = isAdmin;
        this.isAuthenticated = isAuthenticated;
        this.isNodeCertificateRequest = isNodeCertificateRequest;
    }


    public WhoAmIResponse() {
        super();
        this.dn = null;
        this.isAdmin = false;
        this.isAuthenticated = false;
        this.isNodeCertificateRequest = false;
    }

    public WhoAmIResponse(StreamInput in) throws IOException {
        super(in);
        dn = in.readString();
        isAdmin = in.readBoolean();
        isAuthenticated = in.readBoolean();
        isNodeCertificateRequest = in.readBoolean();
    }

    @Override
    public void writeTo(StreamOutput out) throws IOException {
        out.writeString(dn);
        out.writeBoolean(isAdmin);
        out.writeBoolean(isAuthenticated);
        out.writeBoolean(isNodeCertificateRequest);
    }

    public String getDn() {
        return dn;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public boolean isNodeCertificateRequest() {
        return isNodeCertificateRequest;
    }

    @Override
    public XContentBuilder toXContent(XContentBuilder builder, Params params) throws IOException {
        
        builder.startObject("whoami"); 
        builder.field("dn", dn);
        builder.field("is_admin", isAdmin);
        builder.field("is_authenticated", isAuthenticated);
        builder.field("is_node_certificate_request", isNodeCertificateRequest);
        builder.endObject();

        return builder;
    }

    @Override
    public String toString() {
        return Strings.toString(this, true, true);
    }
}
