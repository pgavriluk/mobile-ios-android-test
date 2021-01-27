package api;

import enums.AccountType;
import global.api.MemberRequests;
import global.entity.Member;

public class CreateMember {

    public Member createMember(AccountType accountType) {
        MemberRequests memberRequests = new MemberRequests();
        Member member = memberRequests.createNewMember(accountType);
        memberRequests.activateMember(member);
        memberRequests.resetNewMemberPassword(member);

        return member;
    }

}
