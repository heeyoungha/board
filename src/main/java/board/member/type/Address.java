package board.member.type;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor
public class Address {

    private String address1;
    private String address2;
    private String zipcode;

    public Address(String address1, String address2){
        this.address1 = address1;
        this.address2 = address2;
        this.zipcode = zipcode;
    }

    @Builder
    public Address(String address1, String address2, String zipcode){
        this.address1 = address1;
        this.address2 = address2;
        this.zipcode = zipcode;
    }

}
