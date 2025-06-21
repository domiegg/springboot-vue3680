<template>
<div>
    <Card>
        <div slot="title">
            <div class="edit-head">
                <a @click="close" class="back-title">
                    <Icon type="ios-arrow-back" />返回
                </a>
                <div class="head-name">编辑</div>
                <span></span>
                <a @click="close" class="window-close">
                    <Icon type="ios-close" size="31" class="ivu-icon-ios-close" />
                </a>
            </div>
        </div>
        <Form ref="form" :model="form" :label-width="100" :rules="formValidate" label-position="left">
            <FormItem label="电话卡号码" prop="number">
                <Input v-model="form.number" clearable maxlength="240" show-word-limit placeholder="请输入电话卡号码..." style="width:570px" />
            </FormItem>
            <FormItem label="所属门店" prop="shop">
                <Select v-model="form.shop" clearable placeholder="请选择所属门店..." style="width:570px">
                    <Option value="门店1">门店1</Option>
                    <Option value="门店2">门店2</Option>
                    <Option value="门店3">门店3</Option>
                    <Option value="门店4">门店4</Option>
                    <Option value="门店5">门店5</Option>
                </Select>
            </FormItem>
            <FormItem label="状态" prop="status">
                <Input v-model="form.status" readonly maxlength="240" show-word-limit placeholder="电话卡状态" style="width:570px" />
            </FormItem>
            <FormItem label="预订人" prop="userName">
                <Input v-model="form.userName" readonly maxlength="240" show-word-limit placeholder="没有预订人" style="width:570px" />
            </FormItem>
            <FormItem label="套餐" prop="pa">
                <Select v-model="form.pa" clearable placeholder="请选择套餐..." style="width:570px">
                    <Option v-for="(item,index) in packageList" :key="index" :value="item.title">{{ item.title }}</Option>
                </Select>
            </FormItem>
            <Form-item class="br">
                <Button @click="handleSubmit" :loading="submitLoading" type="primary">提交并保存</Button>
                <Button @click="handleReset">重置</Button>
                <Button type="dashed" @click="close">关闭</Button>
            </Form-item>
        </Form>
    </Card>
</div>
</template>

<script>
import {
    editCallingCard,
    getCallingPackageList
} from "./api.js";
export default {
    name: "edit",
    components: {},
    props: {
        data: Object
    },
    data() {
        return {
            submitLoading: false,
            form: {
                number: "",
                shop: "",
                status: "",
                userId: "",
                userName: "",
                pa: "",
            },
            formValidate: {},
            packageList: []
        };
    },
    methods: {
        init() {
            this.getCallingPackageListFx();
            this.handleReset();
            this.form = this.data;
        },
        getCallingPackageListFx() {
            var that = this;
            that.packageList = [];
            getCallingPackageList().then(res => {
                if (res.success) {
                    that.packageList = res.result;
                }
            })
        },
        handleReset() {
            this.$refs.form.resetFields();
        },
        handleSubmit() {
            this.$refs.form.validate(valid => {
                if (valid) {
                    editCallingCard(this.form).then(res => {
                        this.submitLoading = false;
                        if (res.success) {
                            this.$Message.success("操作成功");
                            this.submited();
                        }
                    });
                }
            });
        },
        close() {
            this.$emit("close", true);
        },
        submited() {
            this.$emit("submited", true);
        }
    },
    mounted() {
        this.init();
    }
};
</script>

<style lang="less">
.edit-head {
    display: flex;
    align-items: center;
    justify-content: space-between;
    position: relative;

    .back-title {
        color: #515a6e;
        display: flex;
        align-items: center;
    }

    .head-name {
        display: inline-block;
        height: 20px;
        line-height: 20px;
        font-size: 16px;
        color: #17233d;
        font-weight: 500;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }

    .window-close {
        z-index: 1;
        font-size: 12px;
        position: absolute;
        right: 0px;
        top: -5px;
        overflow: hidden;
        cursor: pointer;

        .ivu-icon-ios-close {
            color: #999;
            transition: color .2s ease;
        }
    }

    .window-close .ivu-icon-ios-close:hover {
        color: #444;
    }
}
</style>
