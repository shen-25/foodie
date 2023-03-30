package com.zengshen.common.exception;

import com.zengshen.common.enums.BusinessExceptionEnum;

/**
 * @author word
 */
public class BusinessException {
        /**
         * @author word
         * 统一的异常返回，统一封装
         */
        public static void display(BusinessExceptionEnum businessExceptionEnum) {
            throw new MyCustomException(businessExceptionEnum);
        }
}
