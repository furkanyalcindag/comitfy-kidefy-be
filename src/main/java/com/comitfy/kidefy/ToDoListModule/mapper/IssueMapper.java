package com.comitfy.kidefy.ToDoListModule.mapper;

import com.comitfy.kidefy.ToDoListModule.dto.IssueDTO;
import com.comitfy.kidefy.ToDoListModule.dto.requestDTO.IssueRequestDTO;
import com.comitfy.kidefy.ToDoListModule.entity.Issue;
import com.comitfy.kidefy.ToDoListModule.service.BoardColumnService;
import com.comitfy.kidefy.userModule.entity.User;
import com.comitfy.kidefy.userModule.service.UserService;
import com.comitfy.kidefy.util.common.AutoCompleteDTO;
import com.comitfy.kidefy.util.common.BaseMapper;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface IssueMapper extends BaseMapper<IssueDTO, IssueRequestDTO, Issue> {


    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "uuid", ignore = true),
            @Mapping(target = "deleted", ignore = true),
            @Mapping(target = "creationDate", ignore = true),
            @Mapping(target = "updatedDate", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "lastModifiedBy", ignore = true)
    })
    @Override
    void update(@MappingTarget Issue entity, Issue updateEntity);


    @Mappings({
            //@Mapping(target = "proposalUUID", expression = "java(setProposalUUID(entity,proposalService))"),

            @Mapping(target = "user", expression = "java(setUser(entity,userService))"),

            //@Mapping(target = "materialDTO", expression = "java(setMaterial(entity,materialService))")
    })
    IssueDTO entityToDTONew(Issue entity,
                                      @Context UserService userService);



    @Mappings({
            @Mapping(source = "uuid", target = "value"),

            @Mapping(source = "name", target = "label")
    })
    AutoCompleteDTO entityToAutoCompleteDTO(Issue entity);

    default Issue requestDTOToEntity(IssueRequestDTO dto, @Context UserService userService, @Context BoardColumnService boardColumnService){

        Issue issue = new Issue();
        issue.setName(dto.getName());
        issue.setDescription(dto.getDescription());
        issue.setPriority(dto.getPriority());
        issue.setBoardColumnId(boardColumnService.findEntityByUUID(dto.boardColumnUUID).getId());
        issue.setTags(dto.getTags());

        if(dto.getUserUUID()!=null){
            issue.setUserId(userService.findEntityByUUID(dto.getUserUUID()).getId());
        }


        return issue;
    }



    default String setUser(Issue entity, UserService userService) {


        User user = userService.findEntityById(entity.getUserId());



        return user.getFirstName()+" "+user.getLastName();

    }


}
