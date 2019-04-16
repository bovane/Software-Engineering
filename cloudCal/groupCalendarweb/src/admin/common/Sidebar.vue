<template>
    <div class="sidebar">
        <el-menu :default-active="onRoutes" class="el-menu-vertical-demo" theme="dark" unique-opened router>
            <template v-for="item in items">
                <template v-if="item.subs">
                    <el-submenu :index="item.index">
                        <template slot="title"><i :class="item.icon"></i>{{ item.title }}</template>
                        <el-menu-item v-for="(subItem,i) in item.subs" :key="i" :index="subItem.index">{{ subItem.title }}
                        </el-menu-item>
                    </el-submenu>
                </template>
                <template v-else>
                    <el-menu-item :index="item.index">
                        <i :class="item.icon"></i>{{ item.title }}
                    </el-menu-item>
                </template>
            </template>
        </el-menu>
        <div><span style="font-size: 15px;margin-left: 60px;color: white">我的群组</span>
            <el-table :data="data" border style="width:100%;" ref="multipleTable" max-height="294" @row-click="more">
                <el-table-column label="群组名" prop="groupname" show-overflow-tooltip ></el-table-column>
            </el-table>
        </div>
        <el-dialog
            width="30%"
            title="群组信息"
            :visible.sync="dialogFormVisibleed">
            <div>
                <el-form :model="form" :rules="rules" ref="form" label-width="90px" style="margin-left: 60px">
                    <el-form-item prop="groupname" label="群组名 :">
                        <span>{{form.groupname}}</span>
                    </el-form-item>
                    <el-form-item prop="number" label="成员数 :">
                        <span>{{form.number}}</span><el-button type="text" @click="list(form)" style="margin-left: 20px">成员信息</el-button>
                    </el-form-item>
                    <el-form-item prop="createtime"label="创建时间 :">
                        <span>{{form.createtime}}</span>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="invite(form)">邀请成员</el-button>
                        <el-button type="danger" @click="exit(form)">退出本群</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </el-dialog>
        <el-dialog
            width="30%"
            title="成员邀请"
            :visible.sync="dialogFormVisibleed1">
            <div>
                <el-form  :inline="true" :model="form1" :rules="rules1" ref="form1" style="margin-top: 20%;margin-left: 12%">
                    <el-form-item label="搜索 :" prop="username" style="margin-top: -16%">
                        <el-input clearable style="width: 200%" placeholder="请输入用户名" v-model="form1.username" @keyup.enter.native="search(form1)" >
                        </el-input>
                    </el-form-item>
                    <el-form-item style="margin-left: 29%;margin-top: -16%">
                        <el-button type="primary" @click="search(form1)">邀请</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </el-dialog>
        <el-dialog
            title="成员信息"
            :visible.sync="dialogFormVisibleed2"
            style="margin-left:-370px;width:150%;margin-top: -70px">
            <div>
                <el-table :data="data1" border style="width: 100%"ref="multipleTable" >
                    <el-table-column label="成员名称" prop="username" show-overflow-tooltip></el-table-column>
                    <el-table-column label="级别" prop="level" show-overflow-tooltip></el-table-column>
                    <!--<el-table-column label="入群时间" prop="createtime"width="180px" ></el-table-column>-->
                    <el-table-column label="操作" width="180px">
                        <template slot-scope="scope" width="100px">
                            <el-button type="text" @click="change(scope.row)" >改变权限</el-button>
                            <el-button type="text" @click="del(scope.row)" >踢出本群</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
        </el-dialog>
        <el-dialog
            title="权限变更"
            :visible.sync="dialogFormVisibleed3"
            style="margin-left:-70px;width:100%;margin-top: -70px">
            <div>
                <el-form :inline="true" :model="form2" :rules="rules2" ref="form2" style="margin-top: 20%;margin-left: 12%;margin-bottom: 50px">
                    <el-form-item label="权限变更" prop="level" style="margin-top: -10%;margin-left: 10%">
                        <el-select v-model="form2.level" placeholder="请选择">
                            <el-option label="群主" value='1'></el-option>
                            <el-option label="管理员" value='3'></el-option>
                            <el-option label="成员" value='9'></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item style="margin-top: -10%;margin-left: -1%">
                        <el-button type="primary" @click="submit(form2)">确认</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                data:[],
                data1:[],
                items: [
                    {
                        icon: 'el-icon-setting',
                        index: 'calendar',
                        title: '事件管理',
                    },
                    {
                        icon: 'el-icon-setting',
                        index: 'calendar',
                        title: '群组管理',
                        subs:[
                            {
                                index: 'sgroup',
                                title: '群组搜索',
                            },
                            {
                                index: 'cgroup',
                                title: '群组创建',
                            },
                        ]
                    },
                    {
                        icon: 'el-icon-setting',
                        index: 'message',
                        title: '消息管理',
                    },
                ],
                group:[
                    {
                        groupname:'A组',
                        number:3,
                        createtime:"2018-11-26"
                    },
                    {
                        groupname:'浙江工商组',
                        number:33,
                        createtime:"2018-11-26"
                    },
                    {
                        groupname:'努力学习组',
                        number:33,
                        createtime:"2018-11-26"
                    },
                    {
                        groupname:'电竞娱乐组',
                        number:33,
                        createtime:"2018-11-26"
                    },
                    {
                        groupname:'努力学习组',
                        number:33,
                        createtime:"2018-11-26"
                    },
                    {
                        groupname:'电竞娱乐组',
                        number:33,
                        createtime:"2018-11-26"
                    },
                ],
                users:[
                    {
                        username:'admin',
                        level:1,
                    },
                    {
                        username:'小明',
                        level:2,
                    },
                    {
                        username:'小红',
                        level:1,
                    },
                ],
                user:[
                    {
                        username:'admin',
                        level:'1',
                        createtime:'2018-11-28 12:00:00'
                    },
                    {
                        username:'小明',
                        level:'3',
                        createtime:'2018-11-28 15:00:00'
                    },                    {
                        username:'小红',
                        level:'9',
                        createtime:'2018-11-28 16:00:00'
                    }
                ],
                dialogFormVisibleed:false,
                dialogFormVisibleed1:false,
                dialogFormVisibleed2:false,
                dialogFormVisibleed3:false,
                form:{
                    groupname:'',
                    number:'',
                    createtime:'',
                },
                rules:{

                },
                form1:{
                    username:'',
                },
                rules1:{

                },
                form2:{
                    level:'',
                    username:'',
                    groupname:'',
                },
                rules2:{

                },
            }
        },
        created(){
            this.xuanran()
            this.data=this.group
        },
        computed:{
            onRoutes(){
                return this.$route.path.replace('/','');
            }
        },
        methods:{
            xuanran(){
                let params={
                    "username":localStorage.getItem('ms_username')
                }
                this.$http.post("/demo/gusers/mygrp",params,{emulateJSON:true}).then(
                    success=>{
                        this.data=success.data
                    }
                )
            },
            more(row, event, column){
                let params={
                    "groupid":row.groupid,
                }
                this.$http.post("/demo/gusers/count",params,{emulateJSON:true}).then(
                    success=>{
                        this.form.number=success.data.num
                        this.dialogFormVisibleed=true
                    }
                )
                this.form=row
                this.form.createtime=row.create_time
            },
            exit(form){
                this.$confirm('请确认是否要退出该群！', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.exit1(form)
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消'
                    });
                });
            },
            exit1(form){
                let lev =''
                let params={
                    "groupname":this.form.groupname,
                    "username":localStorage.getItem('ms_username'),
                }
                this.$http.post("/demo/gusers/find",params,{emulateJSON:true}).then(
                    success=>{
                        lev=success.data[0].level
                        if(lev=='1'){
                            this.data1[i].level='群主'
                        }else if(lev=='3'){
                            this.data1[i].level='管理员'
                        }else if(lev=='9'){
                            this.data1[i].level='成员'
                        }
                    }
                )
                if(lev=='群主'){
                    this.$message({
                        type: 'error',
                        message: '检测到您为该群群主，请转移权限后再退出该群！'
                    });
                }else{
                    let params={
                        "groupname":this.form.groupname,
                        "username":localStorage.getItem('ms_username'),
                    }
                    this.$http.post("/demo/gusers/del",params,{emulateJSON:true}).then(
                        success=>{
                            this.$message({
                                type: 'success',
                                message: '退出成功！'
                            });
                        }
                    )
                }
            },
            invite(form){
                this.form1.username=''
                this.dialogFormVisibleed1=true
                this.dialogFormVisibleed=false
            },
            search(form1){
                let params={
                    "username":form1.username,
                }
                this.$http.post("/demo/users/list",params,{emulateJSON:true}).then(
                    success=>{
                        if(success.data.length>0){
                            let params1={
                                "sender":localStorage.getItem('ms_username'),
                                "receiver":form1.username,
                                "type":5,
                                "eventid":0,
                                "groupname":this.form.groupname,
                            }
                            this.$http.post("/demo/message/addmeg",params1,{emulateJSON:true}).then(
                                success=>{
                                    this.$message({
                                        type:'success',
                                        message:'邀请信息已发送，等候用户确认！'
                                    })
                                    this.dialogFormVisibleed1=false
                                    this.dialogFormVisibleed=true
                                }
                            )
                        }else{
                            this.$message({
                                type:'warning',
                                message:'该用户名不存在,请确认输入是否正确！'
                            })
                        }
                    }
                )

            },
            list(form){
                console.log(form)
                this.dialogFormVisibleed2=true
                let params={
                    "groupname":form.groupname,
                }
                this.$http.post("/demo/gusers/list",params,{emulateJSON:true}).then(
                    success=>{
                        this.data1=success.data
                        for(let i=0;i<this.data1.length;i++){
                            if(this.data1[i].level=='1'){
                                this.data1[i].level='群主'
                            }else if(this.data1[i].level=='3'){
                                this.data1[i].level='管理员'
                            }else if(this.data1[i].level=='9'){
                                this.data1[i].level='成员'
                            }
                        }
                    }
                )

            },
            change(row){
                let lev =''
                for(let i=0;i<this.data1.length;i++){
                    if(localStorage.getItem('ms_username')==this.data1[i].username){
                        lev = this.data1[i].level
                        i=this.data1.length-1
                    }
                }
                if(lev=='群主'){
                    this.dialogFormVisibleed3=true
                    this.form2.level=row.level
                    this.form2.username=row.username
                }else{
                    this.$message({
                        type: 'warning',
                        message: '权限不足,无法使用此功能！'
                    });
                }
            },
            del(row){
                let lev =''
                for(let i=0;i<this.data1.length;i++){
                    if(localStorage.getItem('ms_username')==this.data1[i].username){
                        lev = this.data1[i].level
                        i=this.data1.length-1
                    }
                }
                if(lev=='群主'){
                    if(row.username==localStorage.getItem('ms_username')){
                        this.$message({
                            type: 'warning',
                            message: '无法将自己踢出本群！'
                        });
                    }else{
                        this.$confirm('请确认是否要踢出该成员！', '提示', {
                            confirmButtonText: '确定',
                            cancelButtonText: '取消',
                            type: 'warning'
                        }).then(() => {
                            this.del1(row)
                        }).catch(() => {
                            this.$message({
                                type: 'info',
                                message: '已取消'
                            });
                        });
                    }
                }else{
                    this.$message({
                        type: 'warning',
                        message: '权限不足,无法使用此功能！'
                    });
                }

            },
            del1(row){
                console.log(row)
                let params={
                    "groupname":this.form.groupname,
                    "username":row.username,
                }
                this.$http.post("/demo/gusers/del",params,{emulateJSON:true}).then(
                    success=>{
                        this.$message({
                            type: 'success',
                            message: '踢出成功！'
                        });
                    }
                )
                this.dialogFormVisibleed2=false
            },
            submit(form2){
                let params={
                    "groupname":this.form.groupname,
                    "username":form2.username,
                    "level":form2.level,
                }
                this.$http.post("/demo/gusers/upgus",params,{emulateJSON:true}).then(
                    success=>{
                        this.$message({
                            type: 'success',
                            message: '修改成功！'
                        });
                    }
                )
                this.dialogFormVisibleed3=false
                this.dialogFormVisibleed2=false
            },
        }
    }
</script>

<style scoped>
    .sidebar{
        display: block;
        position: absolute;
        width: 200px;
        left: 0;
        top: 70px;
        bottom:0px;
        background: #324157;
    }
    .sidebar > ul {
        height:55%;
    }
    .el-scrollbar__thumb {
        display: none;
    }
    .el-scrollbar__wrap {
        overflow-x: hidden;
        overflow-y: auto;
    }

</style>
