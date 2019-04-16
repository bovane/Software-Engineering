<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-setting"></i> 消息管理</el-breadcrumb-item>
                <el-breadcrumb-item>我的消息</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div>
            <el-table :data="data" border style="width: 90%;margin-bottom: 50px"ref="multipleTable" max-height="250">
                <el-table-column label="消息内容" prop="content" show-overflow-tooltip></el-table-column>
                <el-table-column label="时间" prop="createtime" width="180px" sortable></el-table-column>
                <el-table-column label="操作" width="120px" >
                    <template slot-scope="scope" width="40px">
                        <el-button type="text" @click="accept(scope.row)">接受</el-button>
                        <el-button type="text" @click="reject(scope.row)">拒绝</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>
        <div>
            <el-table :data="data1" border style="width: 90%;"ref="multipleTable" max-height="250">
                <el-table-column label="消息内容" prop="content" show-overflow-tooltip></el-table-column>
                <el-table-column label="时间" prop="createtime" width="180px" sortable></el-table-column>
            </el-table>
        </div>
    </div>
</template>

<script>
    export default {
        data: function(){
            return {
                data:[],
                data1:[],
                message:[
                    {
                        content:'A组的管理员admin给你添加了"明天早上开会"的事件！',
                        createtime:'2018-11-28 18:30:12',
                        type:1,//群组事件
                    },
                    {
                        content:'用户admin申请加入浙江工商组！',
                        createtime:'2018-12-28 22:30:12',
                        type:3,//申请加群
                    },
                    {
                        content:'A组的成员admin邀请你加入该小组！',
                        createtime:'2018-10-22 15:30:12',
                        type:5,//邀请入群
                    },
                    {
                        content:'您的"周末去爬山"事件即将发生，请提前做好准备！',
                        createtime:'2018-11-25 12:30:00',
                        type:7,//事件提醒
                    },
                ]
            }
        },
        created(){
            if(localStorage.getItem('ms_username')==""){
                this.$router.replace('/login')
            }else{
                this.xuanran()
            }
        },
        methods:{
            xuanran(){
                let params={
                    "receiver":localStorage.getItem('ms_username'),
                }
                this.$http.post("/demo/message/findmeg",params,{emulateJSON:true}).then(
                    success=>{
                        if(success.data.length!=0){
                            for(let i=0;i<success.data.length;i++){
                                let aa=[]
                                if(success.data[i].type=='3'&&success.data[i].is_accept==0){
                                    aa={
                                        "content":"用户"+success.data[i].sender+"申请加入"+success.data[i].groupname+"!",
                                        "createtime":success.data[i].createtime,
                                        "groupname":success.data[i].groupname,
                                        "username":success.data[i].sender,
                                        "messageid":success.data[i].messageid,
                                        "sender":success.data[i].sender,
                                        "receiver":success.data[i].receiver,
                                    }
                                    this.data.push(aa)
                                }else if(success.data[i].type=='5'&&success.data[i].is_accept==0){
                                    aa={
                                        "content":success.data[i].groupname+"的成员"+success.data[i].sender+"邀请你加入改组!",
                                        "createtime":success.data[i].createtime,
                                        "groupname":success.data[i].groupname,
                                        "username":success.data[i].receiver,
                                        "messageid":success.data[i].messageid,
                                        "sender":success.data[i].sender,
                                        "receiver":success.data[i].receiver,
                                    }
                                    this.data.push(aa)
                                }else if(success.data[i].type=='2'){
                                    let ctime=success.data[i].createtime
                                    aa={
                                        "content":"恭喜你，已成功加入"+ success.data[0].groupname+"!",
                                        "createtime":ctime,
                                    }
                                    this.data1.push(aa)
                                }else if(success.data[i].type=='4'){
                                    let ctime=success.data[i].createtime
                                    aa={
                                        "content":"您已拒绝加入"+ success.data[0].groupname+"!",
                                        "createtime":ctime,
                                    }
                                    this.data1.push(aa)
                                }else if(success.data[i].type=='1'){
                                    let gname=success.data[i].groupname
                                    let ser=success.data[i].sender
                                    let ctime=success.data[i].createtime
                                    let params1={
                                        "eventid":success.data[i].eventid,
                                    }
                                    this.$http.post("/demo/calendar/find",params1,{emulateJSON:true}).then(
                                        success=>{
                                            aa={
                                                "content":gname+"的管理员"+ser+"给你添加了“"+ success.data[0].topic+"“的事件!",
                                                "createtime":ctime,
                                            }
                                            this.data1.push(aa)
                                        }
                                    )
                                }else if(success.data[i].type=='7'){
                                    let ctime=success.data[i].createtime
                                    let params1={
                                        "eventid":success.data[i].eventid,
                                    }
                                    this.$http.post("/demo/calendar/find",params1,{emulateJSON:true}).then(
                                        success=>{
                                            aa={
                                                "content":"您的“"+success.data[0].topic+"”事件即将发生，请提前做好准备！",
                                                "createtime":ctime,
                                            }
                                            this.data1.push(aa)
                                        }
                                    )
                                }
                            }
                        }
                    }
                )

            },
            accept(row){
                console.log(row)
                let params={
                    "groupname":row.groupname,
                    "username":row.username,
                }
                this.$http.post("demo/gusers/addgus",params,{emulateJSON:true}).then(
                    success=>{
                    }
                )
                let params1={
                    "messageid":row.messageid,
                    "is_accept":1,
                }
                this.$http.post("demo/message/upmeg",params1,{emulateJSON:true}).then(
                    success=>{
                    }
                )
                let params2={
                    "eventid":0,
                    "sender":row.receiver,
                    "receiver":row.sender,
                    "groupname":row.groupname,
                    "type":2,
                }
                this.$http.post("/demo/message/addmeg",params2,{emulateJSON:true}).then(
                    success=>{
                        this.$message({
                            type:'success',
                            message:'接受请求成功！'
                        })
                    }
                )
                this.xuanran()
            },
            reject(row){
                let params={
                    "messageid":row.messageid,
                    "is_accept":2,
                }
                this.$http.post("demo/message/upmeg",params,{emulateJSON:true}).then(
                    success=>{
                    }
                )
                let params1={
                    "eventid":0,
                    "sender":row.receiver,
                    "receiver":row.sender,
                    "groupname":row.groupname,
                    "type":4,
                }
                this.$http.post("/demo/message/addmeg",params1,{emulateJSON:true}).then(
                    success=>{
                        this.$message({
                            type:'info',
                            message:'您已拒绝该请求！'
                        })
                    }
                )
                this.xuanran()
            },
        }
    }
</script>

<style scoped>

</style>
