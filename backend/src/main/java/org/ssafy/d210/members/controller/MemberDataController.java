package org.ssafy.d210.members.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.ssafy.d210._common.exception.ErrorResponse;
import org.ssafy.d210._common.response.ApiResponseDto;
import org.ssafy.d210._common.response.MsgType;
import org.ssafy.d210._common.response.ResponseUtils;
import org.ssafy.d210._common.service.UserDetailsImpl;
import org.ssafy.d210.members.dto.request.AdditionalInfo;
import org.ssafy.d210.members.dto.response.ResAdditionalInfo;
import org.ssafy.d210.members.entity.Members;
import org.ssafy.d210.members.service.MemberDataService;
import org.ssafy.d210.members.service.MemberService;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberDataController {

    private final MemberService memberService;
    private final MemberDataService memberDataService;
    @GetMapping("/check-duplicated")
    public ApiResponseDto<Map<String, Boolean>> checkDuplicated (@RequestParam("nickname")String nickname) {
        boolean isDuplicated = memberService.isDuplicatedID(nickname);

        Map<String, Boolean> ret = new HashMap<>();
        ret.put("isDuplicated", isDuplicated);

        log.info("중복 확인 완료 {}의 중복 여부는 {} 입니다. ξ´-ﻌ-`Ҙ", nickname, isDuplicated);
        return ResponseUtils.ok(ret, MsgType.SEARCH_SUCCESSFULLY);
    }

    @PostMapping("/")
    public ApiResponseDto<?> postAdditionalInfo (@RequestBody @Valid  AdditionalInfo addInfo, BindingResult bindingResult, @AuthenticationPrincipal UserDetailsImpl userDetails) {


        if(bindingResult.hasErrors()){
            log.info("{}", bindingResult.getFieldErrors());
            return ResponseUtils.error(ErrorResponse.of(400, "유효하지 않은 값이 있습니다.\n" + bindingResult.getFieldErrors()));
        }

        log.info("객체 바인딩이 잘 되었을까요? 바인딩된 객체는 {}",addInfo);

        Members member = memberDataService.addAdditionalInfo(addInfo, userDetails);

        ResAdditionalInfo ans = new ResAdditionalInfo();
        ans.setId(member.getId());

        return ResponseUtils.ok(ans, MsgType.ADD_INFO_SUCCESSFULLY);
    }

    @GetMapping("/lastlogin")
    public ApiResponseDto<?> getLastLogin(@AuthenticationPrincipal UserDetailsImpl userDetails){





        return null;
    }
}
