package top.anly.common.util.generator;

import lombok.extern.slf4j.Slf4j;
import top.anly.common.util.CommonUtils;

/**
 * 根据表结构，生成代码
 *
 * @author ：yinhaijign
 * @date ：2019/5/14 08:55
 */
@Slf4j
@SuppressWarnings(value = "all")
public class AutoCreateClass {
    private static String BASE_PACKAGE = "top.anly.";
    private static String BASE_PACKAGE_BUSINESS = BASE_PACKAGE + "business.";
    private static String BASE_FILEPATH = "src/main/java/";
    private static String fileRoot = "/";

    private ConvertInfo info;
    private String idName;
    private String packName;
    private String packAllName;
    private String entityName;
    private String lowerEntityName;
    private String entityAllName;
    ;
    String daoName;
    String daoAllName;

    private String iBizName;
    private String iBizAllName;

    String bizName;
    String bizAllName;

    String webName;
    String webAllName;
    String addParamName;
    String modifyParamName;
    String listParamName;
    String listPageParamName;
    String resName;

    public AutoCreateClass(ConvertInfo info) {
        this.info = info;
        packName = info.getPackName();
        packAllName = BASE_PACKAGE_BUSINESS + info.getPackName() + ".";
        entityName = info.getClassName();
        lowerEntityName = OrmBiz.firstToLowerCase(entityName);

        bizName = entityName + "ServiceImpl";
        bizAllName = packAllName + "Service.impl." + bizName;

        iBizName = entityName + "Service";
        iBizAllName = BASE_PACKAGE_BUSINESS + packName + ".service." + iBizName;

        daoName = entityName + "Dao";
        daoAllName = BASE_PACKAGE_BUSINESS + packName + ".dao." + daoName;

        entityAllName = BASE_PACKAGE_BUSINESS + packName + ".domain." + entityName;

        webName = entityName + "Controller";
        webAllName = BASE_PACKAGE_BUSINESS + packName + ".controller." + webName;

        listParamName = BASE_PACKAGE_BUSINESS + packName + ".controller.param." + entityName + "ListParam";
        listPageParamName = BASE_PACKAGE_BUSINESS + packName + ".controller.param." + entityName + "ListPageParam";
        addParamName = BASE_PACKAGE_BUSINESS + packName + ".controller.param." + entityName + "AddParam";
        modifyParamName = BASE_PACKAGE_BUSINESS + packName + ".controller.param." + entityName + "ModifyParam";
        resName = BASE_PACKAGE_BUSINESS + packName + ".controller.res." + entityName + "Res";

        idName = getIdName();
    }

    private String getIdName() {
        for (ConvertColumn cc : info.getColist()) {
            if (cc.isID()) {
                return cc.getFieldName();
            }
        }
        return null;
    }

    private String getPackageName() {
        return packName.substring(packName.lastIndexOf(".") + 1);
    }

    public StringBuilder saveActionHeader() {
        StringBuilder strFile = new StringBuilder();
        String bizName = OrmBiz.firstToLowerCase(iBizName);
        // 包名
        strFile.append("package " + packAllName + "controller;\n");
        strFile.append("\n");
        // 引用包// import
        strFile.append("import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;\n");
        strFile.append("import com.baomidou.mybatisplus.core.metadata.IPage;\n");
        strFile.append("import com.baomidou.mybatisplus.core.toolkit.Wrappers;\n");
        strFile.append("import com.baomidou.mybatisplus.extension.plugins.pagination.Page;\n");
        strFile.append("import " + BASE_PACKAGE + "common.BaseAdminController;\n");
        strFile.append("import " + BASE_PACKAGE + "common.util.Result;\n");
        strFile.append("import " + BASE_PACKAGE + "exception.AppError;\n");
        strFile.append("import " + BASE_PACKAGE + "exception.AppException;\n");
        strFile.append("import " + BASE_PACKAGE + "common.param.IdParam;\n");
        strFile.append("import io.swagger.annotations.Api;\n");
        strFile.append("import io.swagger.annotations.ApiOperation;\n");
        strFile.append("import lombok.extern.slf4j.Slf4j;\n");
        strFile.append("import org.apache.commons.lang3.StringUtils;\n");
        strFile.append("import org.springframework.beans.factory.annotation.Autowired;\n");
        strFile.append("import org.springframework.validation.BindingResult;\n");
        strFile.append("import org.springframework.web.bind.annotation.*;\n");

        strFile.append("import javax.validation.Valid;\n");
        strFile.append("import java.util.List;\n");

        strFile.append("import " + iBizAllName + ";\n");
        strFile.append("import " + entityAllName + ";\n");
        strFile.append("import " + addParamName + ";\n");
        strFile.append("import " + modifyParamName + ";\n");
        strFile.append("import " + listParamName + ";\n");
        strFile.append("import " + listPageParamName + ";\n");
        strFile.append("import " + resName + ";\n");

        strFile.append("\n");
        strFile.append("\n");
        //注释
        toAuthor(strFile, "Controller层接口");

        // 类名
        String lowerName = OrmBiz.firstToLowerCase(entityName);

        String lowerListParamName = OrmBiz.firstToLowerCase(listParamName);
        String lowerListPageParamName = OrmBiz.firstToLowerCase(listPageParamName);

        strFile.append("@Api(tags =\"" + info.getContent() + "接口\")\n");
        strFile.append("@Slf4j\n");
        strFile.append("@RestController\n");
        strFile.append("@RequestMapping(value=\"/" + getPackageName() + "/" + lowerName + "\",method = RequestMethod.POST)\n");
        strFile.append("public  class " + webName + " extends BaseAdminController{\n");
        strFile.append("\n");
        strFile.append("\t@Autowired\n");
        strFile.append("\tprivate " + iBizName + " " + bizName + ";\n");
        strFile.append("\n");

//		//校验入参
//		strFile.append("\t/** \n");
//		strFile.append("\t * 入参校验 \n");
//		strFile.append("\t * @param bindingResult\n");
//		strFile.append("\t */ \n");
//		strFile.append("\tprivate void checkFieldValue(BindingResult bindingResult) {\n");
//		strFile.append("\t\tif (bindingResult.hasErrors()) {\n");
//		strFile.append("\t\t\tString defaultMessage = bindingResult.getFieldError().getDefaultMessage();\n");
//		strFile.append("\t\t\tlog.error(\"入参校验失败[{}],错误信息：[{}]\", bindingResult.toString(), defaultMessage);\n");
//		strFile.append("\t\t\tthrow new AppException(AppError.INVALID_PARAMS, defaultMessage);\n");
//		strFile.append("\t\t}\n");
//		strFile.append("\t}\n");
//		strFile.append("\n");
//		strFile.append("\n");

        //List列表查询接口
        strFile.append("\t/** \n");
        strFile.append("\t * 列表查询接口 \n");
        strFile.append("\t * \n");
        strFile.append("\t * @param \n");
        strFile.append("\t * @return \n");
        strFile.append("\t */ \n");
        strFile.append("\t@ApiOperation(\"列表查询接口\") \n");
        strFile.append("\t@RequestMapping(\"/listByCondition\") \n");
        strFile.append("\t@ResponseBody \n");
        strFile.append("\tpublic Result<List<" + entityName + "Res>> listByCondition(@RequestBody @Valid " + entityName + "ListParam " + lowerName + "ListParam, BindingResult bindingResult) { \n");
        strFile.append("\t\tcheckFieldValue(bindingResult);\n");
        strFile.append("\t\tLambdaQueryWrapper<" + entityName + "> queryWrapper = Wrappers.lambdaQuery();\n");
        strFile.append("\t\tList<" + entityName + "> " + lowerName + "List=" + bizName + ".list(queryWrapper);\n");
        strFile.append("\t\treturn Result.ok(domianConvertRes(" + lowerName + "List," + entityName + "Res.class));\n");
        strFile.append("\t}\n");
        strFile.append("\n");
        strFile.append("\n");

        //分页查询接口
        strFile.append("\t/** \n");
        strFile.append("\t * 分页查询接口 \n");
        strFile.append("\t * \n");
        strFile.append("\t * @param \n");
        strFile.append("\t * @return \n");
        strFile.append("\t */ \n");
        strFile.append("\t@ApiOperation(\"分页查询接口\") \n");
        strFile.append("\t@RequestMapping(\"/pageList\") \n");
        strFile.append("\t@ResponseBody \n");
        strFile.append("\tpublic Result<IPage<" + entityName + "Res>> pageList(@RequestBody @Valid " + entityName + "ListPageParam " + lowerName + "ListPageParam, BindingResult bindingResult) { \n");
        strFile.append("\t\tcheckFieldValue(bindingResult);\n");
        strFile.append("\t\tLambdaQueryWrapper<" + entityName + "> queryWrapper = Wrappers.lambdaQuery();\n");
        strFile.append("\t\tIPage<" + entityName + "> pages = " + bizName + ".page(new Page<>(" + lowerName + "ListPageParam.getPageNumber(), " + lowerName + "ListPageParam.getPageSize()), queryWrapper);\n");
        strFile.append("\t\treturn Result.ok(domianConvertRes(pages," + entityName + "Res.class));\n");
        strFile.append("\t}\n");
        strFile.append("\n");
        strFile.append("\n");
        return strFile;
    }

    public void saveActionClass() {
        String lowerName = OrmBiz.firstToLowerCase(entityName);

        String bizName = OrmBiz.firstToLowerCase(iBizName);
        String filePath = info.getSavePath();
        if (filePath.endsWith(fileRoot) == false) {
            filePath += "/";
        }
        filePath += BASE_FILEPATH + webAllName.replaceAll("\\.", "/") + ".java";
        StringBuilder strFile = this.saveActionHeader();

        //保存接口
        strFile.append("\t/** \n");
        strFile.append("\t * 保存接口 \n");
        strFile.append("\t * \n");
        strFile.append("\t * @param \n");
        strFile.append("\t * @return \n");
        strFile.append("\t */ \n");
        strFile.append("\t@ApiOperation(\"保存接口\") \n");
        strFile.append("\t@RequestMapping(\"/save\") \n");
        strFile.append("\t@ResponseBody \n");
        strFile.append("\tpublic Result save(@RequestBody @Valid " + entityName + "AddParam " + lowerName + "AddParam, BindingResult bindingResult) { \n");
        strFile.append("\t\tcheckFieldValue(bindingResult);\n");
        strFile.append("\t\tboolean tag = " + bizName + ".save(objectConvert(" + lowerName + "AddParam,new " + entityName + "())); \n");
        strFile.append("\t\treturn tag ? Result.ok() : Result.fail();\n");
        strFile.append("\t}\n");
        strFile.append("\n");
        strFile.append("\n");

        //修改接口
        strFile.append("\t/** \n");
        strFile.append("\t * 修改接口 \n");
        strFile.append("\t * \n");
        strFile.append("\t * @param \n");
        strFile.append("\t * @return \n");
        strFile.append("\t */ \n");
        strFile.append("\t@ApiOperation(\"修改接口\") \n");
        strFile.append("\t@RequestMapping(\"/update\") \n");
        strFile.append("\t@ResponseBody \n");
        strFile.append("\tpublic Result update(@RequestBody @Valid " + entityName + "ModifyParam " + lowerName + "ModifyParam, BindingResult bindingResult) { \n");
        strFile.append("\t\tcheckFieldValue(bindingResult);\n");
        strFile.append("\t\tboolean tag = " + bizName + ".updateById(objectConvert(" + lowerName + "ModifyParam,new " + entityName + "())); \n");
        strFile.append("\t\treturn tag ? Result.ok() : Result.fail();\n");
        strFile.append("\t}\n");
        strFile.append("\n");
        strFile.append("\n");

        //批量删除接口
        strFile.append("\t/** \n");
        strFile.append("\t * 批量删除接口 \n");
        strFile.append("\t * \n");
        strFile.append("\t * @param \n");
        strFile.append("\t * @return \n");
        strFile.append("\t */ \n");
        strFile.append("\t@ApiOperation(\"批量删除接口\") \n");
        strFile.append("\t@RequestMapping(\"/deleteBatch\") \n");
        strFile.append("\t@ResponseBody \n");
        strFile.append("\tpublic Result delete(@RequestBody @Valid List<IdParam> idParam, BindingResult bindingResult) { \n");
        strFile.append("\t\tcheckFieldValue(bindingResult);\n");
        strFile.append("\t\t" + bizName + ".batchRemoveByIds(idParam); \n");
        strFile.append("\t\treturn Result.ok();\n");
        strFile.append("\t}\n");
        strFile.append("\n");
        strFile.append("\n");

        // 类结束符
        strFile.append("}\n");

        if (CommonUtils.writeFile(filePath, strFile.toString())) {
            log.debug(filePath + "写入磁盘成功");
        } else {
            log.debug(filePath + "写入磁盘失败");
        }
    }


    public void saveIBizClass() {
        String lowerName = OrmBiz.firstToLowerCase(entityName);

        //确定文件路径
        String filePath = info.getSavePath();
        if (filePath.endsWith(fileRoot) == false) {
            filePath += "/";
        }
        filePath += BASE_FILEPATH + iBizAllName.replaceAll("\\.", "/") + ".java";


        StringBuilder strFile = new StringBuilder();
        // 包名
        strFile.append("package " + packAllName + "service;\n");
        strFile.append("\n");
        // import
        strFile.append("import com.baomidou.mybatisplus.core.metadata.IPage;\n");
        strFile.append("import com.baomidou.mybatisplus.extension.service.IService;\n");
        strFile.append("import " + BASE_PACKAGE + "common.param.IdParam;\n");
        strFile.append("import " + BASE_PACKAGE + "common.util.Result;\n");
        strFile.append("import java.util.List;\n");
        strFile.append("import " + entityAllName + ";\n");
        strFile.append("import " + addParamName + ";\n");
        strFile.append("import " + modifyParamName + ";\n");
        strFile.append("import " + listParamName + ";\n");
        strFile.append("import " + listPageParamName + ";\n");
        strFile.append("import " + resName + ";\n");

        strFile.append("\n");
        strFile.append("\n");

        //注释
        toAuthor(strFile, "SERVICE层接口");

        // 类名
        strFile.append("public interface " + iBizName
                + " extends IService<" + entityName + "> {\n");
        strFile.append("\n");

        strFile.append("\t/**\n");
        strFile.append("\t * 批量删除\n");
        strFile.append("\t * @param params\n");
        strFile.append("\t */\n");
        strFile.append("\tvoid batchRemoveByIds(List<IdParam> params);\n");
        strFile.append("\n");


        strFile.append("\n");
        // 类结束符
        strFile.append("}\n");

        if (CommonUtils.writeFile(filePath, strFile.toString())) {
            log.debug(filePath + "写入磁盘成功");
        } else {
            log.debug(filePath + "写入磁盘失败");
        }
    }

    public void saveBizClass() {
        String lowerName = OrmBiz.firstToLowerCase(entityName);
        String filePath = info.getSavePath();
        if (filePath.endsWith(fileRoot) == false) {
            filePath += "/";
        }

        filePath += BASE_FILEPATH + bizAllName.replaceAll("\\.", "/") + ".java";

        StringBuilder strFile = new StringBuilder();
        // 包名
        strFile.append("package " + packAllName + "service.impl;\n");
        strFile.append("\n");

        // 引用包
        // import
        strFile.append("import com.baomidou.mybatisplus.core.metadata.IPage;\n");
        strFile.append("import com.baomidou.mybatisplus.extension.plugins.pagination.Page;\n");
        strFile.append("import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;\n");
        strFile.append("import org.springframework.beans.BeanUtils;\n");
        strFile.append("import org.springframework.beans.factory.annotation.Autowired;\n");
        strFile.append("import org.springframework.stereotype.Service;\n");
        strFile.append("import org.springframework.transaction.annotation.Transactional;\n");
        strFile.append("import java.util.ArrayList;\n");
        strFile.append("import java.util.List;\n");
        strFile.append("import java.util.stream.Collectors;\n");

        strFile.append("import " + entityAllName + ";\n");
        strFile.append("import " + daoAllName + ";\n");
        strFile.append("import " + iBizAllName + ";\n");
        strFile.append("import " + addParamName + ";\n");
        strFile.append("import " + modifyParamName + ";\n");
        strFile.append("import " + listParamName + ";\n");
        strFile.append("import " + listPageParamName + ";\n");
        strFile.append("import " + resName + ";\n");
        strFile.append("import " + BASE_PACKAGE + "common.param.IdParam;\n");
        strFile.append("import " + BASE_PACKAGE + "exception.AppError;\n");
        strFile.append("import " + BASE_PACKAGE + "exception.AppException;\n");

        strFile.append("\n");
        strFile.append("\n");

        //注释
        toAuthor(strFile, "SERVICE层接口实现类");

        // 类名
        strFile.append("@Service\n");
        strFile.append("public class " + bizName + " extends ServiceImpl<"
                + daoName + "," + entityName + "> implements " + iBizName + " {\n");

        strFile.append("\n");


        strFile.append("\t@Override\n");
        strFile.append("\tpublic void batchRemoveByIds(List<IdParam> params) {\n");
        strFile.append("\t\tList<Integer> ids = params.stream().map(IdParam::getId).collect(Collectors.toList());\n");
        strFile.append("\t\tif(!removeByIds(ids)){\n");
        strFile.append("\t\t\tthrow new AppException(AppError.FAILED);\n");
        strFile.append("\t\t}\n");
        strFile.append("\t}\n");
        strFile.append("\n");


        strFile.append("\n");
        // 类结束符
        strFile.append("}\n");

        if (CommonUtils.writeFile(filePath, strFile.toString())) {
            log.debug(filePath + "写入磁盘成功");
        } else {
            log.debug(filePath + "写入磁盘失败");
        }

    }

    public void saveDaoClass() {
        String filePath = info.getSavePath();
        if (filePath.endsWith(fileRoot) == false) {
            filePath += "/";
        }
        filePath += BASE_FILEPATH + daoAllName.replaceAll("\\.", "/") + ".java";
        StringBuilder strFile = new StringBuilder();
        // 包名
        strFile.append("package " + packAllName + "dao;\n");
        strFile.append("\n");
        // import
        strFile.append("import com.baomidou.mybatisplus.core.mapper.BaseMapper;\n");
        strFile.append("import org.springframework.stereotype.Repository;\n");
        strFile.append("import " + entityAllName + ";\n");
        strFile.append("\n");
        strFile.append("\n");

        //注释
        toAuthor(strFile, "DAO层接口");

        // 类名
        strFile.append("@Repository\n");
        strFile.append("public interface " + daoName + " extends BaseMapper<" + entityName + "> {\n");
        // 类结束符
        strFile.append("}\n");

        if (CommonUtils.writeFile(filePath, strFile.toString())) {
            log.debug(filePath + "写入磁盘成功");
        } else {
            log.debug(filePath + "写入磁盘失败");
        }

    }


    public void saveEntityClass() {

        String filePath = info.getSavePath();
        if (filePath.endsWith(fileRoot) == false) {
            filePath += "/";
        }
        filePath += BASE_FILEPATH + entityAllName.replaceAll("\\.", "/") + ".java";
        StringBuilder strFile = new StringBuilder();
        StringBuilder setgetBuf = new StringBuilder();

        // 包名
        strFile.append("package " + packAllName + "domain;\n");
        strFile.append("\n");
        strFile.append("import com.baomidou.mybatisplus.annotation.IdType;\n");
        strFile.append("import com.baomidou.mybatisplus.annotation.TableId;\n");
        strFile.append("import com.baomidou.mybatisplus.annotation.TableName;\n");
        strFile.append("import top.anly.common.BaseDomain;\n");
        strFile.append("import com.fasterxml.jackson.annotation.JsonFormat;\n");
        strFile.append("import lombok.Data;\n");
        strFile.append("import java.time.LocalDateTime;\n");
        strFile.append("\n");
        strFile.append("\n");

        //注释
        toAuthor(strFile, "实体类");

        // 类名
        strFile.append("@TableName(\"" + info.getTableName() + "\")\n");
        strFile.append("@Data\n");
        strFile.append("public class " + entityName + " extends BaseDomain {\n");

        for (ConvertColumn col : info.getColist()) {
            String fname = col.getFieldName();
            if ("gmtCreate".equals(fname) || "gmtModified".equals(fname) || "companyId".equals(fname)) {
                continue;
            }
            // 字段注释
            strFile.append("\t/**\n");
            strFile.append("\t * " + col.getComment() + "\n");
            strFile.append("\t */\n");
            if (col.isAutoIncrement()) {

                strFile.append("\t@TableId(type = IdType.AUTO)\n");
            }

            if ("java.time.LocalDateTime".equals(col.getFieldType())) {
                strFile.append("\t@JsonFormat(pattern=\"yyyy-MM-dd HH:mm:ss\",timezone = \"GMT+8\")\n");
            }
            strFile.append("\tprivate " + col.getFieldType() + " " + fname
                    + ";\n");
            strFile.append("\n");

        }

        strFile.append("\n");
        strFile.append("\n");

        strFile.append(setgetBuf);

        // 类结束符
        strFile.append("}\n");

        if (CommonUtils.writeFile(filePath, strFile.toString())) {
            log.debug(filePath + "写入磁盘成功");
        } else {
            log.debug(filePath + "写入磁盘失败");
        }
    }

    public void saveListParamClass() {

        String filePath = info.getSavePath();
        if (filePath.endsWith(fileRoot) == false) {
            filePath += "/";
        }
        filePath += BASE_FILEPATH + listParamName.replaceAll("\\.", "/") + ".java";
        StringBuilder strFile = new StringBuilder();
        StringBuilder setgetBuf = new StringBuilder();

        // 包名
        strFile.append("package " + packAllName + "controller.param;\n");
        strFile.append("\n");
        strFile.append("import com.fasterxml.jackson.annotation.JsonFormat;\n");
        strFile.append("import lombok.Data;\n");
        strFile.append("import java.time.LocalDateTime;\n");
        strFile.append("import io.swagger.annotations.ApiModel;\n");
        strFile.append("import io.swagger.annotations.ApiModelProperty;\n");
        strFile.append("\n");
        strFile.append("\n");

        //注释
        toAuthor(strFile, "列表查询入参");

        // 类名
        strFile.append("@ApiModel(\"" + info.getContent() + "列表查询入参\")\n");
        strFile.append("@Data\n");
        strFile.append("public class " + entityName + "ListParam {\n");

        for (ConvertColumn col : info.getColist()) {
            String fname = col.getFieldName();
            if ("gmtCreate".equals(fname) || "gmtModified".equals(fname)) {
                continue;
            }
            // 字段注释
            strFile.append("\t/**\n");
            strFile.append("\t * " + col.getComment() + "\n");
            strFile.append("\t */\n");

            if ("java.time.LocalDateTime".equals(col.getFieldType())) {
                strFile.append("\t@JsonFormat(pattern=\"yyyy-MM-dd HH:mm:ss\",timezone = \"GMT+8\")\n");
            }

            strFile.append("\t@ApiModelProperty(value = \"" + col.getComment() + "\", example = \"\")\n");

            strFile.append("\tprivate " + col.getFieldType() + " " + fname
                    + ";\n");
            strFile.append("\n");

        }

        strFile.append("\n");
        strFile.append("\n");

        strFile.append(setgetBuf);

        // 类结束符
        strFile.append("}\n");

        if (CommonUtils.writeFile(filePath, strFile.toString())) {
            log.debug(filePath + "写入磁盘成功");
        } else {
            log.debug(filePath + "写入磁盘失败");
        }
    }

    public void saveListPageParamClass() {

        String filePath = info.getSavePath();
        if (filePath.endsWith(fileRoot) == false) {
            filePath += "/";
        }
        filePath += BASE_FILEPATH + listPageParamName.replaceAll("\\.", "/") + ".java";
        StringBuilder strFile = new StringBuilder();
        StringBuilder setgetBuf = new StringBuilder();

        // 包名
        strFile.append("package " + packAllName + "controller.param;\n");
        strFile.append("\n");
        strFile.append("import com.fasterxml.jackson.annotation.JsonFormat;\n");
        strFile.append("import lombok.Data;\n");
        strFile.append("import java.time.LocalDateTime;\n");
        strFile.append("import io.swagger.annotations.ApiModel;\n");
        strFile.append("import io.swagger.annotations.ApiModelProperty;\n");
        strFile.append("import javax.validation.constraints.NotNull;\n");
        strFile.append("\n");
        strFile.append("\n");

        //注释
        toAuthor(strFile, "分页查询入参");

        // 类名
        strFile.append("@ApiModel(\"" + info.getContent() + "分页查询入参\")\n");
        strFile.append("@Data\n");
        strFile.append("public class " + entityName + "ListPageParam extends " + entityName + "ListParam{\n");

        // 字段注释
        strFile.append("\t/**\n");
        strFile.append("\t * 当前页码\n");
        strFile.append("\t */\n");
        strFile.append("\t@ApiModelProperty(value = \"当前页码\", example = \"1\",required = true)\n");
        strFile.append("\t@NotNull(message = \"当前页码为空\")\n");
        strFile.append("\tprivate Integer pageNumber;\n");

        strFile.append("\t/**\n");
        strFile.append("\t * 每页数量\n");
        strFile.append("\t */\n");
        strFile.append("\t@ApiModelProperty(value = \"每页数量\", example = \"10\",required = true)\n");
        strFile.append("\t@NotNull(message = \"每页数量为空\")\n");
        strFile.append("\tprivate Integer pageSize;\n");

        strFile.append("\n");
        strFile.append("\n");

        strFile.append(setgetBuf);

        // 类结束符
        strFile.append("}\n");

        if (CommonUtils.writeFile(filePath, strFile.toString())) {
            log.debug(filePath + "写入磁盘成功");
        } else {
            log.debug(filePath + "写入磁盘失败");
        }
    }

    public void saveAddParamClass() {

        String filePath = info.getSavePath();
        if (filePath.endsWith(fileRoot) == false) {
            filePath += "/";
        }
        filePath += BASE_FILEPATH + addParamName.replaceAll("\\.", "/") + ".java";
        StringBuilder strFile = new StringBuilder();
        StringBuilder setgetBuf = new StringBuilder();

        // 包名
        strFile.append("package " + packAllName + "controller.param;\n");
        strFile.append("\n");
        strFile.append("import com.fasterxml.jackson.annotation.JsonFormat;\n");
        strFile.append("import lombok.Data;\n");
        strFile.append("import java.time.LocalDateTime;\n");
        strFile.append("import io.swagger.annotations.ApiModel;\n");
        strFile.append("import io.swagger.annotations.ApiModelProperty;\n");
        strFile.append("\n");
        strFile.append("\n");

        //注释
        toAuthor(strFile, "新增入参");

        // 类名
        strFile.append("@ApiModel(\"" + info.getContent() + "新增入参\")\n");
        strFile.append("@Data\n");
        strFile.append("public class " + entityName + "AddParam {\n");

        for (ConvertColumn col : info.getColist()) {
            String fname = col.getFieldName();
            if ("gmtCreate".equals(fname) || "gmtModified".equals(fname)) {
                continue;
            }
            // 字段注释
            strFile.append("\t/**\n");
            strFile.append("\t * " + col.getComment() + "\n");
            strFile.append("\t */\n");

            if ("java.time.LocalDateTime".equals(col.getFieldType())) {
                strFile.append("\t@JsonFormat(pattern=\"yyyy-MM-dd HH:mm:ss\",timezone = \"GMT+8\")\n");
            }

            strFile.append("\t@ApiModelProperty(value = \"" + col.getComment() + "\", example = \"\")\n");

            strFile.append("\tprivate " + col.getFieldType() + " " + fname
                    + ";\n");
            strFile.append("\n");

        }

        strFile.append("\n");
        strFile.append("\n");

        strFile.append(setgetBuf);

        // 类结束符
        strFile.append("}\n");

        if (CommonUtils.writeFile(filePath, strFile.toString())) {
            log.debug(filePath + "写入磁盘成功");
        } else {
            log.debug(filePath + "写入磁盘失败");
        }
    }

    public void saveModifyParamClass() {

        String filePath = info.getSavePath();
        if (filePath.endsWith(fileRoot) == false) {
            filePath += "/";
        }
        filePath += BASE_FILEPATH + modifyParamName.replaceAll("\\.", "/") + ".java";
        StringBuilder strFile = new StringBuilder();
        StringBuilder setgetBuf = new StringBuilder();

        // 包名
        strFile.append("package " + packAllName + "controller.param;\n");
        strFile.append("\n");
        strFile.append("import com.fasterxml.jackson.annotation.JsonFormat;\n");
        strFile.append("import lombok.Data;\n");
        strFile.append("import java.time.LocalDateTime;\n");
        strFile.append("import io.swagger.annotations.ApiModel;\n");
        strFile.append("import io.swagger.annotations.ApiModelProperty;\n");
        strFile.append("import javax.validation.constraints.NotNull;\n");
        strFile.append("\n");
        strFile.append("\n");

        //注释
        toAuthor(strFile, "修改入参");

        // 类名

        strFile.append("@ApiModel(\"" + info.getContent() + "修改入参\")\n");
        strFile.append("@Data\n");
        strFile.append("public class " + entityName + "ModifyParam {\n");

        for (ConvertColumn col : info.getColist()) {
            String fname = col.getFieldName();
            if ("gmtCreate".equals(fname) || "gmtModified".equals(fname)) {
                continue;
            }
            // 字段注释
            strFile.append("\t/**\n");
            strFile.append("\t * " + col.getComment() + "\n");
            strFile.append("\t */\n");

            if ("java.time.LocalDateTime".equals(col.getFieldType())) {
                strFile.append("\t@JsonFormat(pattern=\"yyyy-MM-dd HH:mm:ss\",timezone = \"GMT+8\")\n");
            }

            strFile.append("\t@ApiModelProperty(value = \"" + col.getComment() + "\", example = \"\")\n");

            strFile.append("\tprivate " + col.getFieldType() + " " + fname
                    + ";\n");
            strFile.append("\n");

        }

        strFile.append("\n");
        strFile.append("\n");

        strFile.append(setgetBuf);

        // 类结束符
        strFile.append("}\n");

        if (CommonUtils.writeFile(filePath, strFile.toString())) {
            log.debug(filePath + "写入磁盘成功");
        } else {
            log.debug(filePath + "写入磁盘失败");
        }
    }

    public void saveResClass() {

        String filePath = info.getSavePath();
        if (filePath.endsWith(fileRoot) == false) {
            filePath += "/";
        }
        filePath += BASE_FILEPATH + resName.replaceAll("\\.", "/") + ".java";
        StringBuilder strFile = new StringBuilder();
        StringBuilder setgetBuf = new StringBuilder();

        // 包名
        strFile.append("package " + packAllName + "controller.res;\n");
        strFile.append("\n");
        strFile.append("import com.fasterxml.jackson.annotation.JsonFormat;\n");
        strFile.append("import lombok.Data;\n");
        strFile.append("import java.time.LocalDateTime;\n");
        strFile.append("import io.swagger.annotations.ApiModel;\n");
        strFile.append("import io.swagger.annotations.ApiModelProperty;\n");
        strFile.append("\n");
        strFile.append("\n");

        //注释
        toAuthor(strFile, "返回属性");

        // 类名
        strFile.append("@ApiModel(\"" + info.getContent() + "返回属性\")\n");
        strFile.append("@Data\n");
        strFile.append("public class " + entityName + "Res {\n");

        for (ConvertColumn col : info.getColist()) {
            String fname = col.getFieldName();
            if ("gmtCreate".equals(fname) || "gmtModified".equals(fname)) {
                continue;
            }
            // 字段注释
            strFile.append("\t/**\n");
            strFile.append("\t * " + col.getComment() + "\n");
            strFile.append("\t */\n");

            if ("java.time.LocalDateTime".equals(col.getFieldType())) {
                strFile.append("\t@JsonFormat(pattern=\"yyyy-MM-dd HH:mm:ss\",timezone = \"GMT+8\")\n");
            }
            strFile.append("\t@ApiModelProperty(value = \"" + col.getComment() + "\", example = \"\")\n");

            strFile.append("\tprivate " + col.getFieldType() + " " + fname
                    + ";\n");
            strFile.append("\n");

        }

        strFile.append("\n");
        strFile.append("\n");

        strFile.append(setgetBuf);

        // 类结束符
        strFile.append("}\n");

        if (CommonUtils.writeFile(filePath, strFile.toString())) {
            log.debug(filePath + "写入磁盘成功");
        } else {
            log.debug(filePath + "写入磁盘失败");
        }
    }

    private void toAuthor(StringBuilder strFile, String methordName) {
        //注释
        strFile.append("/**\n" +
                " * " + info.getContent() + methordName + "\n" +
                " *\n" +
                " * @author ：" + info.getAuthor() + "\n" +
                " * @date ：" + info.getCreatDate() + "\n" +
                " */\n");
    }
}
