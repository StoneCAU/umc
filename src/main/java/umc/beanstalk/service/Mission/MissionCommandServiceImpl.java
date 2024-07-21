package umc.beanstalk.service.Mission;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.beanstalk.apiPayload.code.status.ErrorStatus;
import umc.beanstalk.apiPayload.exception.handler.StoreHandler;
import umc.beanstalk.converter.MissionConverter;
import umc.beanstalk.domain.Mission;
import umc.beanstalk.domain.Store;
import umc.beanstalk.repository.MemberRepository;
import umc.beanstalk.repository.MissionRepository;
import umc.beanstalk.repository.StoreRepository;
import umc.beanstalk.web.dto.Mission.MissionRequestDTO;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionCommandServiceImpl implements MissionCommandService {

    private final StoreRepository storeRepository;

    private final MissionRepository missionRepository;

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Mission joinMission(MissionRequestDTO.MissionJoinDto request) {

        Store newStore = storeRepository.findById(request.getStoreId()).orElseThrow(
                () -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND)
        );

        Mission newMission = MissionConverter.toMission(request, newStore);

        return missionRepository.save(newMission);

    }
}
