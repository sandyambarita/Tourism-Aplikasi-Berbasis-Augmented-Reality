<?php

namespace frontend\models;

use Yii;

/**
 * This is the model class for table "t_achievement".
 *
 * @property integer $achievement_id
 * @property integer $challenge_type_id
 * @property integer $checkpoint_id
 * @property string $hadiah
 * @property string $path_gambar
 *
 * @property TCheckpoint $checkpoint
 * @property TChallengeType $challengeType
 */
class TAchievement extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 't_achievement';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['challenge_type_id', 'checkpoint_id'], 'integer'],
            [['hadiah'], 'string', 'max' => 30],
            [['path_gambar'], 'string', 'max' => 255],
            [['checkpoint_id'], 'exist', 'skipOnError' => true, 'targetClass' => TCheckpoint::className(), 'targetAttribute' => ['checkpoint_id' => 'checkpoint_id']],
            [['challenge_type_id'], 'exist', 'skipOnError' => true, 'targetClass' => TChallengeType::className(), 'targetAttribute' => ['challenge_type_id' => 'challenge_type_id']],
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'achievement_id' => 'Achievement ID',
            'challenge_type_id' => 'Challenge Type ID',
            'checkpoint_id' => 'Checkpoint ID',
            'hadiah' => 'Hadiah',
            'path_gambar' => 'Path Gambar',
        ];
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getCheckpoint()
    {
        return $this->hasOne(TCheckpoint::className(), ['checkpoint_id' => 'checkpoint_id']);
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getChallengeType()
    {
        return $this->hasOne(TChallengeType::className(), ['challenge_type_id' => 'challenge_type_id']);
    }
}
