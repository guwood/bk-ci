/*
 * Tencent is pleased to support the open source community by making BK-CI 蓝鲸持续集成平台 available.
 *
 * Copyright (C) 2019 THL A29 Limited, a Tencent company.  All rights reserved.
 *
 * BK-CI 蓝鲸持续集成平台 is licensed under the MIT license.
 *
 * A copy of the MIT License is included in this file.
 *
 *
 * Terms of the MIT License:
 * ---------------------------------------------------
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy,
 * modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
 * LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN
 * NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.tencent.devops.store.api.common

import com.tencent.devops.common.api.auth.AUTH_HEADER_USER_ID
import com.tencent.devops.common.api.pojo.Page
import com.tencent.devops.common.api.pojo.Result
import com.tencent.devops.store.pojo.common.StoreApproveDetail
import com.tencent.devops.store.pojo.common.StoreApproveInfo
import com.tencent.devops.store.pojo.common.StoreApproveRequest
import com.tencent.devops.store.pojo.common.enums.ApproveStatusEnum
import com.tencent.devops.store.pojo.common.enums.ApproveTypeEnum
import com.tencent.devops.store.pojo.common.enums.StoreTypeEnum
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.HeaderParam
import javax.ws.rs.PUT
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType

@Api(tags = ["USER_MARKET_APPROVAL"], description = "store组件审批")
@Path("/user/market/approval/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
interface UserStoreApproveResource {

    @ApiOperation("工作台-审批组件")
    @PUT
    @Path("/types/{storeType}/codes/{storeCode}/ids/{approveId}/approve")
    fun approveStoreInfo(
        @ApiParam("userId", required = true)
        @HeaderParam(AUTH_HEADER_USER_ID)
        userId: String,
        @ApiParam("组件类型", required = true)
        @PathParam("storeType")
        storeType: StoreTypeEnum,
        @ApiParam("组件标识", required = true)
        @PathParam("storeCode")
        storeCode: String,
        @ApiParam("审批ID", required = true)
        @PathParam("approveId")
        approveId: String,
        @ApiParam("store审批信息请求报文体", required = true)
        storeApproveRequest: StoreApproveRequest
    ): Result<Boolean>

    @ApiOperation("工作台-获取组件审批信息列表")
    @GET
    @Path("/types/{storeType}/codes/{storeCode}/list")
    fun getStoreApproveInfos(
        @ApiParam("userId", required = true)
        @HeaderParam(AUTH_HEADER_USER_ID)
        userId: String,
        @ApiParam("组件类型", required = true)
        @PathParam("storeType")
        storeType: StoreTypeEnum,
        @ApiParam("组件标识", required = true)
        @PathParam("storeCode")
        storeCode: String,
        @ApiParam("申请人", required = false)
        @QueryParam("applicant")
        applicant: String?,
        @ApiParam("审批类型", required = false)
        @QueryParam("approveType")
        approveType: ApproveTypeEnum?,
        @ApiParam("审批状态", required = false)
        @QueryParam("approveStatus")
        approveStatus: ApproveStatusEnum?,
        @ApiParam("页码", required = true)
        @QueryParam("page")
        page: Int,
        @ApiParam("每页数量", required = true)
        @QueryParam("pageSize")
        pageSize: Int
    ): Result<Page<StoreApproveInfo>?>

    @ApiOperation("获取组件审批信息详情")
    @GET
    @Path("/ids/{approveId}")
    fun getStoreApproveDetail(
        @ApiParam("userId", required = true)
        @HeaderParam(AUTH_HEADER_USER_ID)
        userId: String,
        @ApiParam("审批ID", required = true)
        @PathParam("approveId")
        approveId: String
    ): Result<StoreApproveDetail?>

    @ApiOperation("获取用户关于组件的审批信息")
    @GET
    @Path("/types/{storeType}/codes/{storeCode}/user")
    fun getUserStoreApproveInfo(
        @ApiParam("userId", required = true)
        @HeaderParam(AUTH_HEADER_USER_ID)
        userId: String,
        @ApiParam("组件类型", required = true)
        @PathParam("storeType")
        storeType: StoreTypeEnum,
        @ApiParam("组件标识", required = true)
        @PathParam("storeCode")
        storeCode: String,
        @ApiParam("审批类型", required = true)
        @QueryParam("approveType")
        approveType: ApproveTypeEnum
    ): Result<StoreApproveInfo?>
}