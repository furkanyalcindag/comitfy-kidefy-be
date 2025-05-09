package com.comitfy.kidefy.util.common;


import com.comitfy.kidefy.annotation.CheckAuth;
import com.comitfy.kidefy.annotation.ControllerInfo;
import com.comitfy.kidefy.util.PageDTO;
import com.comitfy.kidefy.util.dbUtil.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public abstract class BaseCrudController<DTO extends BaseDTO, RequestDTO extends BaseDTO, Entity extends BaseEntity,
        Repository extends BaseRepository<Entity>, Mapper extends BaseMapper<DTO, RequestDTO, Entity>,
        Specifitation extends BaseSpecification<Entity>,
        Service extends BaseService<DTO, RequestDTO, Entity, Repository, Mapper, Specifitation>> {

     ;
    @Autowired
    HelperService helperService;

    protected abstract Service getService();

    protected abstract Mapper getMapper();

    protected void validate(BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            String errors = bindingResult.getAllErrors().stream().map(n -> n.toString())
                    .collect(Collectors.joining(","));
            throw new Exception(errors);
        }
    }

    protected String getControllerName() {
        ControllerInfo controllerInfo = this.getClass().getAnnotation(ControllerInfo.class);

        if (controllerInfo != null) {
            return controllerInfo.controllerName();
        } else {
            return "Unknown Controller";
        }
    }

    @PostMapping("get-all-by-filter")
    @CheckAuth(permission ="List")
    public ResponseEntity<PageDTO<DTO>> getAll(
    @RequestBody BaseFilterRequestDTO filter) {
        PageDTO<DTO> dtoList = getService().findAll(filter);
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }




    @PostMapping("search-autocomplete-filter")
    @CheckAuth(permission ="search-autocomplete-filter")
    public ResponseEntity<List<AutoCompleteDTO>> searchAutoCompleteByFilter(@RequestHeader(value = "x-active-branch", required = false) UUID activeBranch,
                                                                            @RequestBody BaseFilterRequestDTO filter) {
        filter.setBranchUUID(activeBranch);
        List<AutoCompleteDTO> dtoList = getService().autoComplete(filter);

        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }


    @GetMapping("/")
    @CheckAuth(permission ="Get")
    public ResponseEntity<PageDTO<DTO>> getAll(@RequestParam int pageNumber, @RequestParam int pageSize) {

        PageDTO<DTO> dtoList = getService().findAll(pageNumber, pageSize);

        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTO> getById(@PathVariable UUID id) {
        DTO optionalT = getService().findByUUID(id);
        if (optionalT == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(optionalT, HttpStatus.OK);
        }
    }

    @PostMapping("/")
    @CheckAuth(permission ="Save")
    public ResponseEntity<DTO> save(@RequestHeader(value = "x-active-branch", required = false) UUID activeBranch,
                                    @RequestBody RequestDTO body) {

        body.setBranchUUID(activeBranch);
        return new ResponseEntity<>(getService().save(body), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @CheckAuth(permission ="delete")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        DTO optional = getService().findByUUID(id);

        if (optional == null) {

            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
                   }


        getService().delete(optional.getUuid());
        return new ResponseEntity<>("Object with the id " + id + " was deleted.", HttpStatus.NO_CONTENT);


    }

    @PutMapping("/{id}")
    @CheckAuth(permission ="Update")
    public ResponseEntity<String> update(@RequestHeader(value = "x-active-branch", required = false) UUID activeBranch,
            @PathVariable UUID id, @RequestBody RequestDTO body) {

        body.setBranchUUID(activeBranch);
        DTO dto = getService().update(id, body);

        if (dto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND);
        } else {

            return new ResponseEntity<>("Object with the id " + id + " was updated.", HttpStatus.OK);
        }

    }


}
