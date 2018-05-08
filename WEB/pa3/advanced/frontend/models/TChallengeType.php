<?php

namespace frontend\models;

use Yii;

/**
 * This is the model class for table "t_challenge_type".
 *
 * @property integer $challenge_type_id
 * @property integer $checkpoint_id
 * @property string $type_name
 * @property string $description
 *
 * @property TAnswer[] $tAnswers
 * @property TCheckpoint $checkpoint
 * @property TQuestion[] $tQuestions
 */
class TChallengeType extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 't_challenge_type';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['type_name'], 'string', 'max' => 30],
            [['description'], 'string'],
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'challenge_type_id' => 'Challenge Type ID',
            'type_name' => 'Type Name',
            'description' => 'Description',
        ];
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getTAnswers()
    {
        return $this->hasMany(TAnswer::className(), ['challenge_type_id' => 'challenge_type_id']);
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
    public function getTQuestions()
    {
        return $this->hasMany(TQuestion::className(), ['challenge_type_id' => 'challenge_type_id']);
    }
}
