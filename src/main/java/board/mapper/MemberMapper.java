package board.mapper;

import board.member.Member;
import board.member.MemberRequest;
import board.member.MemberResponse;
import board.member.type.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    Member toMember(MemberRequest.CreateMemberRequest memberRequest);

    MemberResponse toMemberResponse(Member member);

    Address toAddress(MemberRequest.CreateMemberRequest.MemberAddressRequest address);

    MemberResponse.MemberAddressResponse toMemberAddressResponse(Address address);

    Member toUpdateMember(MemberRequest.UpdateMemberRequest memberRequest);
}
