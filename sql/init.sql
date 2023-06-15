create table ec_offer.ec_bank
(
    bank_id       int(6)           not null
        primary key,
    parent_id     int(6)           null,
    bank_name     varchar(120)     not null,
    bank_code     varchar(120)     null,
    comments      varchar(255)     null,
    state         char             not null,
    state_date    datetime         not null,
    BANK_CARD     varchar(120)     null comment '银行卡号',
    VERIFY_METHOD char default '1' null comment '验证方式 1-短信 2-金额'
)
    charset = utf8;
