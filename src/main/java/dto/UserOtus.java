
package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@AllArgsConstructor
@Data
@Builder


public class UserOtus {

    private String email;
    private String firstName;
    private long id;
    private String lastName;
    private String password;
    private String phone;
    private long userStatus;
    private String username;

}
